/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.agent;

import cn.enaium.cafully.util.LocationUtil;
import org.tinylog.configuration.ConfigurationLoader;

import java.io.File;
import java.util.Properties;

/**
 * @author Enaium
 * @since 1.0.0
 */
public class LogConfig implements ConfigurationLoader {

    private static final String FORMAT = "{date:yyyy-MM-dd HH:mm:ss} [{level}] [{thread}] ({tag}) > {message}";

    @Override
    public Properties load() throws Exception {
        final Properties properties = new Properties();
        properties.setProperty("writer1", "console");
        properties.setProperty("writer1.format", FORMAT);
        properties.setProperty("writer1.level", "INFO");
        properties.setProperty("writer2", "file");
        properties.setProperty("writer2.format", FORMAT);
        properties.setProperty("writer2.charset", "UTF-8");
        properties.setProperty("writer2.append", "true");
        properties.setProperty("writer2.file", new File(LocationUtil.getDirectory(Agent.class), "latest.log").getAbsolutePath());
        return properties;
    }
}
