/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.helper;

import java.io.File;
import java.util.Properties;

/**
 * @author Enaium
 * @since 1.0.0
 */
public interface IConfigHelper {
    /**
     * @return The directory to the config file
     * @since 1.0.0
     */
    File directory();

    /**
     * @return The path to the config file
     * @since 1.0.0
     */
    File file();

    /**
     * @return Read the properties to the config file
     * @since 1.0.0
     */
    Properties read();
}
