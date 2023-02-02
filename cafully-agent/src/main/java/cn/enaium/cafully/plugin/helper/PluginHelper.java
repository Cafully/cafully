/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.plugin.helper;

import cn.enaium.cafully.plugin.annotation.Plugin;
import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

import java.util.Collection;

/**
 * @author Enaium
 */
public class PluginHelper implements IPluginHelper {

    private final Plugin annotation;
    private final Collection<IHelper> helpers;

    public PluginHelper(Plugin annotation, Collection<IHelper> helpers) {
        this.annotation = annotation;
        this.helpers = helpers;
    }

    @Override
    public Collection<IHelper> all() {
        return helpers;
    }

    @Override
    public TaggedLogger logger() {
        return Logger.tag(annotation.unique());
    }

    @Override
    public Plugin annotation() {
        return annotation;
    }
}
