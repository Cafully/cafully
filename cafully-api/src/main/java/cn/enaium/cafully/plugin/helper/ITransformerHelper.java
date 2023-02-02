/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.helper;

import cn.enaium.cafully.plugin.api.ITransformer;

import java.util.List;

/**
 * @author Enaium
 * @since 1.0.0
 */
public interface ITransformerHelper {
    /**
     * @return transformer list
     * @since 1.0.0
     */
    List<ITransformer> all();

    /**
     * @param transformer transformer
     * @since 1.0.0
     */
    void add(ITransformer transformer);
}
