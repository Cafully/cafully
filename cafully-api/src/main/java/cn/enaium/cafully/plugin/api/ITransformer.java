/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.api;

import java.security.ProtectionDomain;

/**
 * @author Enaium
 * @since 1.0.0
 */
public interface ITransformer {
    /**
     * @param name class name
     * @return is support
     * @since 1.0.0
     */
    boolean supportClass(String name);

    default int priority() {
        return Byte.MAX_VALUE;
    }

    /**
     * @return transformed class
     * @since 1.0.0
     */
    default byte[] before(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] basic) throws Exception {
        return basic;
    }

    /**
     * @since 1.0.0
     */
    default void after(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] basic) throws Exception {
    }
}
