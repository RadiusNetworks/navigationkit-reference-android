package com.radiusnetworks.navigationkitreference;

import android.util.Log;


import com.radiusnetworks.navigationkit.MapLocation;
import com.radiusnetworks.navigationkit.NavigationKitLocationNotifier;
import com.radiusnetworks.navigationkit.NavigationKitManager;
import com.radiusnetworks.navigationkit.configuration.Configuration;

import org.altbeacon.beacon.BeaconParser;

import java.util.HashMap;


/**
 * The <code>Application</code> class for the Navigation Kit's Reference App. It is
 * ideal to place interactions with the Navigation Kit here, due to the
 * accessibility of the <code>Application</code> class from any Activity.
 *
 * This class implements two required methods for the
 * <code>NavigationKitLocationNotifier, didUpdateMapLocation</code> and
 * <code>didUpdateMapConfig</code>. The <code>NavigationKitManager</code> constructor is
 * called within this class's onCreate method, as is necessary for
 * <code>NavigationKit</code> functionality.
 *
 * @author Matt Tyler
 */

public class NavigationKitReferenceApplication extends android.app.Application implements NavigationKitLocationNotifier {
    private static final String TAG = "NavigationKitReferenceApplication";

    MainActivity mMainActivity;

    @Override
    public void onCreate() {

        NavigationKitManager navigationKitManager = NavigationKitManager.getInstanceForApplication(this);
        navigationKitManager.setNavigationKitLocationNotifier(this);

        java.util.Map<String,String> configurationMap = new HashMap<String,String>();
        configurationMap.put(Configuration.PK_KIT_URL_KEY,"https://proximitykit.radiusnetworks.com/api/kits/4958");
        configurationMap.put(Configuration.PK_API_TOKEN_KEY,"30a377179188d633f4945acfca76ee96f2dc97691e0cfd3db8ece9c0077a685d");
        configurationMap.put(Configuration.NK_MAP_CONFIG_URL_KEY,"https://s3.amazonaws.com/schika.radiusnetworks.com/nav_kit_reference_map_config.json");
        navigationKitManager.setConfiguration(configurationMap);

        navigationKitManager.start();

    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }
    /**
     * Invoked when a new map location is available.
     * @param mapLocation Represents a location on a map.
     */

    @Override
    public void didUpdateMapLocation(MapLocation mapLocation) {
        Log.d(TAG,"map location updated");

        // user location has changed
    }
    /**
     * Invoked when maps and map configurations have been updated from the server.
     */

    @Override
    public void didUpdateMapConfig() {
        Log.d(TAG,"map configuration updated");

        // more map files may have been updated or added.

    }
}
