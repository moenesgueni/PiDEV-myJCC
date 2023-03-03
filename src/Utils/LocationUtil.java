/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author dhia
 */
import javax.microedition.location.*;

public class LocationUtil {
    private static LocationProvider defaultProvider;

    // get the default location provider
    public static LocationProvider getDefaultProvider() throws LocationException {
        if (defaultProvider == null) {
            defaultProvider = LocationProvider.getInstance(null);
        }
        return defaultProvider;
    }
}
