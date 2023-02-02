/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.agent;

import cn.enaium.cafully.plugin.PluginManager;
import cn.enaium.cafully.plugin.api.ITransformer;
import cn.enaium.cafully.util.LoggerUtil;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Comparator;
import java.util.List;

import static cn.enaium.cafully.util.LoggerUtil.LOGGER;

/**
 * @author Enaium
 */
public class DefaultTransformer implements ClassFileTransformer {

    private final PluginManager pluginManager;

    public DefaultTransformer(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] basic) {
        try {
            final List<ITransformer> transformers = pluginManager.transformerHelper.all();
            transformers.sort(Comparator.comparingInt(ITransformer::priority));
            for (ITransformer transformer : transformers) {
                if (!transformer.supportClass(className)) {
                    continue;
                }

                basic = transformer.before(loader, className, classBeingRedefined, protectionDomain, basic);

                transformer.after(loader, className, classBeingRedefined, protectionDomain, basic);
            }

        } catch (Throwable e) {
            LOGGER.error(e);
        }
        return basic;
    }
}
