/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.helper;

import cn.enaium.cafully.plugin.api.ITransformer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Enaium
 * @since 1.0.0
 */
public class TransformerHelper implements ITransformerHelper {

    private final List<ITransformer> transformers = new CopyOnWriteArrayList<>();

    @Override
    public List<ITransformer> all() {
        return transformers;
    }

    public void add(ITransformer transformer) {
        transformers.add(transformer);
    }
}
