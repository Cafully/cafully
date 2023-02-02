/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully;

import java.io.File;
import java.lang.instrument.Instrumentation;

/**
 * @author Enaium
 * @since 1.0.0
 */
public class Environment {
    public final String argument;
    public final Instrumentation instrumentation;
    public final Loader loader;
    public final File runDir;
    public final File pluginDir;
    public final File configDir;

    public Environment(String argument, Instrumentation instrumentation, Loader loader, File runDir) {
        this.argument = argument;
        this.instrumentation = instrumentation;
        this.loader = loader;
        this.runDir = runDir;
        pluginDir = new File(runDir, "plugin");

        if (!pluginDir.exists()) {
            pluginDir.mkdirs();
        }

        configDir = new File(runDir, "config");

        if (!configDir.mkdirs()) {
            configDir.mkdirs();
        }
    }
}
