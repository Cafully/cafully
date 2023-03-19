/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.api;

import org.jetbrains.annotations.Nullable;

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
     * @deprecated name may be null
     */
    @Deprecated
    default boolean supportClass(@Nullable String name) {
        return false;
    }

    /**
     * @return is support
     * @since 1.1.0
     */
    default boolean support(ClassLoader loader, @Nullable String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] basic) {
        return false;
    }

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
