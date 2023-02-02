/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.api;

import cn.enaium.cafully.plugin.helper.IHelper;

/**
 * @author Enaium
 * @since 1.0.0
 */
public interface IInitializer {


    /**
     * the method is invoked before all plugins are initialized
     *
     * @since 1.0.0
     */
    default void before() throws Throwable {

    }

    /**
     * initialize plugin
     *
     * @param plugin more helper
     * @since 1.0.0
     */
    default void initialize(IHelper plugin) throws Throwable {

    }

    /**
     * the method is invoked before all plugins are initialized
     *
     * @since 1.0.0
     */
    default void after() throws Throwable {

    }
}