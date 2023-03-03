package Utils;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.microedition.location.*;

public class APItools implements LocationListener {
    private LocationProvider locationProvider;

    public void getLocation() {
        try {
            // get the default location provider
            locationProvider = LocationUtil.getDefaultProvider();

            // set the location listener
            locationProvider.setLocationListener(this, 10, -1, -1);

            // get the last known location
            Location location = locationProvider.getLastKnownLocation();

            // do something with the location
            if (location != null) {
                double latitude = location.getQualifiedCoordinates().getLatitude();
                double longitude = location.getQualifiedCoordinates().getLongitude();
                System.out.println("Latitude: " + latitude);
                System.out.println("Longitude: " + longitude);
            } else {
                System.out.println("No location data available");
            }
        } catch (LocationException e) {
            System.out.println("Location error: " + e.getMessage());
        }
    }

    public void locationUpdated(LocationProvider provider, Location location) {
        // handle location updates here
    }

    public void providerStateChanged(LocationProvider provider, int state) {
        // handle provider state changes here
    }
}
