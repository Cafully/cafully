/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.agent;

import cn.enaium.cafully.Environment;
import cn.enaium.cafully.Loader;
import cn.enaium.cafully.plugin.PluginManager;
import cn.enaium.cafully.util.LocationUtil;

import java.lang.instrument.Instrumentation;

/**
 * @author Enaium
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        final Package aPackage = Main.class.getPackage();
        System.out.println(aPackage.getImplementationTitle());
        System.out.println(aPackage.getImplementationVersion());
        System.out.println(aPackage.getImplementationVendor());
    }

    public static void agent(String argument, Instrumentation inst, Loader loader) throws Exception {
        final Environment environment = new Environment(argument, inst, loader, LocationUtil.getDirectory(Agent.class));
        final PluginManager pluginManager = new PluginManager(environment);
        pluginManager.load();
        inst.addTransformer(new DefaultTransformer(pluginManager), true);
    }
}
