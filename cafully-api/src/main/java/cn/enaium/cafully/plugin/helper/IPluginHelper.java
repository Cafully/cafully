/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.helper;

import cn.enaium.cafully.plugin.annotation.Plugin;
import org.tinylog.TaggedLogger;

import java.util.Collection;

/**
 * @author Enaium
 * @since 1.0.0
 */
public interface IPluginHelper {
    /**
     * @return all helper
     * @since 1.0.0
     */
    Collection<IHelper> all();

    /**
     * @return tiny logger
     * @since 1.0.0
     */
    TaggedLogger logger();

    /**
     * @since 1.0.0
     */
    Plugin annotation();
}
