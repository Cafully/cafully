/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Enaium
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Plugin {
    /**
     * @return Unique identity
     * @since 1.0.0
     */
    String unique();

    /**
     * @return Name
     * @since 1.0.0
     */
    String name();

    /**
     * Follows the rules of NPM.
     * Supports ^, x, *, ~, and more.
     * See <a href="https://github.com/npm/node-semver">...</a>
     *
     * @return Version
     * @since 1.0.0
     */
    String version();

    /**
     * @return Api Version
     * @since 1.0.0
     */
    String api();

    /**
     * @return Description
     * @since 1.0.0
     */
    String description() default "";

    /**
     * @return Authors
     * @since 1.0.0
     */
    String[] authors() default {};

    /**
     * @return Transformers
     * @since 1.0.0
     */
    Transformer[] transformers() default {};

    /**
     * @return Dependencies
     * @since 1.0.0
     */
    Dependency[] dependencies() default {};

    /**
     * @return Properties
     * @since 1.0.0
     */
    Pair[] properties() default {};
}
