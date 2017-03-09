package android.smurf;

import android.app.Application;
import android.content.Context;

/**
 * Main class for this application
 *
 * @author Wojtek Kolendo
 */

public class SmurfApplication extends Application {

    private static SmurfApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }

    public static Context getContext() {
        return context;
    }
}
