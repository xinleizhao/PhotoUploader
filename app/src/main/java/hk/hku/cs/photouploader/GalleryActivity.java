package hk.hku.cs.photouploader;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import hk.hku.cs.photouploader.BuildConfig;
import hk.hku.cs.photouploader.util.Utils;


public class GalleryActivity extends FragmentActivity {
    private static final String TAG = "GalleryActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);
        
        if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, new GalleryFragment(), TAG);
            ft.commit();
        }
    }
}
