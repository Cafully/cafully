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
public class Helper implements IHelper {

    private final Environment environment;
    private final ITransformerHelper transformer;
    private final IConfigHelper config;
    private final IPluginHelper plugin;

    public Helper(Environment environment, ITransformerHelper transformer, IConfigHelper config, IPluginHelper plugin) {
        this.environment = environment;
        this.transformer = transformer;
        this.config = config;
        this.plugin = plugin;
    }

    @Override
    public Environment environment() {
        return environment;
    }

    @Override
    public ITransformerHelper transformer() {
        return transformer;
    }

    @Override
    public IConfigHelper config() {
        return config;
    }

    @Override
    public IPluginHelper plugin() {
        return plugin;
    }
}
