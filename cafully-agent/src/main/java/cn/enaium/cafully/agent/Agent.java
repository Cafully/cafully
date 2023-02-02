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

package cn.enaium.cafully.agent;


import cn.enaium.cafully.Loader;
import cn.enaium.cafully.util.LocationUtil;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.jar.JarFile;

/**
 * @author Enaium
 * @since 1.0.0
 */
public class Agent {
    public static void premain(String argument, Instrumentation inst) throws Exception {
        agent(argument, inst);
    }

    public static void agentmain(String argument, Instrumentation inst) throws Exception {
        agent(argument, inst);
    }

    private static void agent(String argument, Instrumentation inst) throws Exception {

        inst.appendToBootstrapClassLoaderSearch(new JarFile(new File(LocationUtil.getLocation(Agent.class))));

        Loader loader = new Loader();
        Class<?> main = loader.loadClass(Main.class.getName());

        Method agent = main.getDeclaredMethod("agent", String.class, Instrumentation.class, Loader.class);
        agent.setAccessible(true);
        agent.invoke(null, argument, inst, loader);
    }
}