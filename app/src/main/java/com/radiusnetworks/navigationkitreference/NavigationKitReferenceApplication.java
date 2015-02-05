package com.radiusnetworks.navigationkitreference;

import android.util.Log;


import com.radiusnetworks.navigationkit.MapLocation;
import com.radiusnetworks.navigationkit.NavigationKitLocationNotifier;
import com.radiusnetworks.navigationkit.NavigationKitManager;
import com.radiusnetworks.navigationkit.configuration.Configuration;

import java.util.HashMap;


/**
 * Created by dyoung on 10/30/14.
 */
public class NavigationKitReferenceApplication extends android.app.Application implements NavigationKitLocationNotifier {
    private static final String TAG = "NavigationKitReferenceApplication";

    MainActivity mMainActivity;

    @Override
    public void onCreate() {
        Log.d(TAG,"onCreate");

        NavigationKitManager navigationKitManager = NavigationKitManager.getInstanceForApplication(this);
        navigationKitManager.setNavigationKitLocationNotifier(this);

        java.util.Map<String,String> configurationMap = new HashMap<String,String>();
        configurationMap.put(Configuration.PK_KIT_URL_KEY,"https://proximitykit.radiusnetworks.com/api/kits/4958");
        configurationMap.put(Configuration.PK_API_TOKEN_KEY,"30a377179188d633f4945acfca76ee96f2dc97691e0cfd3db8ece9c0077a685d");
        configurationMap.put(Configuration.NK_MAP_CONFIG_URL_KEY,"https://s3.amazonaws.com/schika.radiusnetworks.com/nav_kit_reference_map_config.json");
        navigationKitManager.setConfiguration(configurationMap);

        Log.d(TAG,"navigationKitManager starting");
        navigationKitManager.start();
        Log.d(TAG,"navigationKitManager started");

    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }

    @Override
    public void didUpdateMapLocation(MapLocation mapLocation) {
        Log.d(TAG,"map location updated");

        // user location has changed
    }

    @Override
    public void didUpdateMapConfig() {
        Log.d(TAG,"map configuration updated");

        // more map files may have been updated or added.

    }
}
