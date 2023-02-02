/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.helper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Enaium
 */
public class ConfigHelper implements IConfigHelper {


    private final File file;

    public ConfigHelper(File file) {
        this.file = file;
    }

    @Override
    public File directory() {
        return file.getParentFile();
    }

    @Override
    public File file() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Create config '%s' fail", file), e);
        }
        return file;
    }

    @Override
    public Properties read() {
        final Properties properties = new Properties();
        try {
            properties.load(new FileReader(file()));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Read config '%s' fail", file()), e);
        }
        return properties;
    }
}
