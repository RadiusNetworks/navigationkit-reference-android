package com.radiusnetworks.navigationkitreference;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.radiusnetworks.navigationkit.MapView;
import com.radiusnetworks.navigationkit.MapViewNotifier;
import com.radiusnetworks.navigationkit.NavigationKitManager;

/**
 * The Main <code>Activity</code> for the Navigation Kit's Reference App.
 *
 * <p>
 * This class utilizes the built-in <code>MapView</code> class along with
 * it's accompanying <code>MapViewNotifier</code>. When used together,
 * these allow a quick and easy method to display user navigation on a
 * pre-configured map. When loaded properly, the MapView will display a
 * user location dot for the user that corresponds with the closest detected
 * beacon.
 * </p>
 * <p>
 * For a list of AltBeacon identifiers that are pre-configured with the map being displayed
 * in this application, see https://github.com/RadiusNetworks/navigationkit-reference-android/blob/master/README.md
 * </p>
 * <p>
 * To see this in action, set up several AltBeacons around your building with those
 * identifiers, and then walk from one to the next to see the location dot on
 * the MapView move along with your change in location.
 * </p>
 *
 * @see com.radiusnetworks.navigationkit.MapView
 * @see com.radiusnetworks.navigationkit.MapViewNotifier
 * @author Matt Tyler
 *
 */
public class MainActivity extends Activity implements MapViewNotifier {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        NavigationKitManager mNavigationKitManager = NavigationKitManager.getInstanceForApplication(this);

        //setting up connection to Radius's built-in MapView class
        MapView mMapView = new MapView(this);
        mMapView.setMapViewNotifier(this);

        //connecting MapView to UI
        ((FrameLayout)this.findViewById(R.id.webview_layout)).addView(mMapView);
        mMapView.loadMap(mNavigationKitManager.findMap("V1"));

        //notifying user of bluetooth limitations for older devices.
        // Bluetooth Low Energy requires Android 4.3 or greater.
        try {
            if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.dialog_title_check_jellybean));
                builder.setMessage(getString(R.string.dialog_message_check_jellybean));
                builder.setPositiveButton(android.R.string.ok, null);
                builder.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }



    //Required methods for MapViewNotifier. These provide feedback from the MapView that is currently being displayed.

    /**
     * Called when the user taps the map. Returns the point in meters.
     * @param x
     * @param y
     */
    @Override
    public void onTapped(double x, double y) {
        Log.d(TAG,"onTapped. x = "+x+ ". y = "+y);
    }

    /**
     * Called when a map finishes loading.
     * @param s
     */
    @Override
    public void onLoaded(String s) {
        Log.d(TAG, "map loaded");
    }

    /**
     * Called when a map fails to load.
     * @param e
     */
    @Override
    public void onLoadFail(Exception e) {
        Log.d(TAG,"map failed to load");
    }
}
