/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Enaium
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Pair {
    /**
     * @since 1.0.0
     */
    String key();

    /**
     * @since 1.0.0
     */
    String value();
}
