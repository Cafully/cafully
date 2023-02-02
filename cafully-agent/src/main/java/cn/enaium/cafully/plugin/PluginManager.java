/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin;

import cn.enaium.cafully.Environment;
import cn.enaium.cafully.agent.Agent;
import cn.enaium.cafully.plugin.annotation.Dependency;
import cn.enaium.cafully.plugin.annotation.Plugin;
import cn.enaium.cafully.plugin.annotation.Transformer;
import cn.enaium.cafully.plugin.api.IInitializer;
import cn.enaium.cafully.plugin.api.ITransformer;
import cn.enaium.cafully.plugin.helper.*;
import com.vdurmont.semver4j.Semver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import static cn.enaium.cafully.util.LoggerUtil.LOGGER;

/**
 * @author Enaium
 * @since 1.0.0
 */
public class PluginManager {

    public final ITransformerHelper transformerHelper = new TransformerHelper();
    private final Environment environment;

    private final Map<IInitializer, IHelper> helpers = new HashMap<>();

    public PluginManager(Environment environment) {
        this.environment = environment;
    }

    public void load() throws RuntimeException {
        LOGGER.info("Load Plugin...");
        try {
            Map<String, IInitializer> initializers = new HashMap<>();

            Files.walkFileTree(environment.pluginDir.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toFile().getName().endsWith(".jar")) {

                        final JarFile jarFile = new JarFile(file.toFile());

                        final Manifest manifest = jarFile.getManifest();

                        if (manifest != null) {
                            final String mainClass = manifest.getMainAttributes().getValue(Attributes.Name.MAIN_CLASS);
                            if (mainClass != null) {
                                try {
                                    if (IInitializer.class.isAssignableFrom(Class.forName(mainClass, false, new URLClassLoader(new URL[]{file.toFile().toURI().toURL()})))) {

                                        environment.loader.addURL(file.toUri().toURL());
                                        environment.instrumentation.appendToSystemClassLoaderSearch(jarFile);

                                        final IInitializer initializer = (IInitializer) Class.forName(mainClass, false, environment.loader).getConstructor().newInstance();

                                        if (initializer.getClass().isAnnotationPresent(Plugin.class)) {
                                            initializers.put(initializer.getClass().getAnnotation(Plugin.class).unique(), initializer);
                                        }
                                    }
                                } catch (Throwable e) {
                                    LOGGER.error(new RuntimeException(String.format("Plugin '%s' load fail", file.toFile().getAbsoluteFile()), e));
                                }
                            }
                        }
                    }
                    return super.visitFile(file, attrs);
                }
            });


            for (IInitializer initializer : initializers.values()) {
                final Plugin annotation = initializer.getClass().getAnnotation(Plugin.class);
                final String cafullyVersion = Agent.class.getPackage().getImplementationVersion();
                final Semver semver = new Semver(cafullyVersion, Semver.SemverType.NPM);
                if (!semver.satisfies(annotation.api())) {
                    throw new RuntimeException(String.format("Plugin '%s' api version not satisfy, expected %s actual %s", annotation.unique(), cafullyVersion, annotation.api()));
                }
            }


            //Before
            for (IInitializer iInitializer : initializers.values()) {
                iInitializer.before();
            }


            //Check Dependencies
            for (IInitializer initializer : initializers.values()) {
                final Plugin annotation = initializer.getClass().getAnnotation(Plugin.class);

                for (Dependency dependency : annotation.dependencies()) {
                    if (!initializers.containsKey(dependency.unique())) {
                        throw new RuntimeException(String.format("Lack of dependency '%s'", dependency.unique()));
                    }

                    final Plugin dependencyPlugin = initializers.get(dependency.unique()).getClass().getAnnotation(Plugin.class);
                    final Semver semver = new Semver(dependencyPlugin.version(), Semver.SemverType.NPM);

                    if (!semver.satisfies(dependency.version())) {
                        throw new RuntimeException(String.format("Dependency '%s' version not satisfy, expected %s actual %s", dependency.unique(), dependency.version(), dependencyPlugin.version()));
                    }
                }

                final IHelper helper = new Helper(environment, transformerHelper, new ConfigHelper(new File(environment.configDir, annotation.unique() + ".properties")), new PluginHelper(annotation, helpers.values()));
                helpers.put(initializer, helper);
            }

            for (Map.Entry<IInitializer, IHelper> entry : helpers.entrySet()) {
                entry.getKey().initialize(entry.getValue());
                final Plugin annotation = entry.getKey().getClass().getAnnotation(Plugin.class);
                for (Transformer transformer : annotation.transformers()) {
                    final Class<?> transformerClass = Class.forName(transformer.value().getName(), false, environment.loader);
                    if (ITransformer.class.isAssignableFrom(transformerClass)) {
                        entry.getValue().transformer().add((ITransformer) transformerClass.getConstructor().newInstance());
                    }
                }
            }

            //Before
            for (IInitializer iInitializer : initializers.values()) {
                iInitializer.after();
            }

            LOGGER.info(String.format("Loaded %s Plugins!", initializers.size()));
        } catch (Throwable e) {
            LOGGER.error(e);
        }
    }
}
