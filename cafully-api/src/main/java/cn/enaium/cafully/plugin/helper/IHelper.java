/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.helper;

import cn.enaium.cafully.Environment;

/**
 * @author Enaium
 * @since 1.0.0
 */
public interface IHelper {
    /**
     * @return more cafully information
     * @since 1.0.0
     */
    Environment environment();

    /**
     * @return transform helper
     * @since 1.0.0
     */
    ITransformerHelper transformer();

    /**
     * @return config helper
     * @since 1.0.0
     */
    IConfigHelper config();

    /**
     * @return plugin annotation
     * @since 1.0.0
     */
    IPluginHelper plugin();
}
