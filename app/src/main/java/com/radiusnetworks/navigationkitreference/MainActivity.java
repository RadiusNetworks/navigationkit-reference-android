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

public class MainActivity extends Activity implements MapViewNotifier {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_main);


        //setting up connection to UI
        NavigationKitManager mNavigationKitManager = NavigationKitManager.getInstanceForApplication(this);

        //setting up connection to Radius's built-in MapView class
        MapView mMapView = new MapView(this);
        mMapView.setMapViewNotifier(this);
        ((FrameLayout)this.findViewById(R.id.webview_layout)).addView(mMapView);
        mMapView.loadMap(mNavigationKitManager.findMap("V1"));

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

    @Override
    public void onTapped(double x, double y) {
        Log.d(TAG,"onTapped. x = "+x+ ". y = "+y);
    }

    @Override
    public void onLoaded(String s) {
        Log.d(TAG, "map loaded");
    }

    @Override
    public void onLoadFail(Exception e) {
        Log.d(TAG,"map failed to load");
    }
}
