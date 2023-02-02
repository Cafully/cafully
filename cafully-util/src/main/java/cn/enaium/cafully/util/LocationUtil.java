/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package cn.enaium.cafully.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Enaium
 * @since 1.0.0
 */
public class LocationUtil {

    /**
     * @since 1.0.0
     */
    public static URI getLocation(Class<?> klass) throws URISyntaxException {
        if (klass.getProtectionDomain().getCodeSource() != null) {
            final URL location = klass.getProtectionDomain().getCodeSource().getLocation();
            if (location != null) {
                return location.toURI();
            }
        }

        String className = "/" + klass.getName().replace(".", "/") + ".class";
        final URL resource = klass.getResource(className);
        if (resource == null) {
            throw new RuntimeException("It's not procuration");
        }

        final String file = resource.getFile();

        return new URI(file.substring(0, file.length() - className.length() - 1));
    }

    /**
     * @since 1.0.0
     */
    public static File getDirectory(Class<?> klass) throws URISyntaxException {
        return new File(getLocation(klass)).getParentFile();
    }
}
