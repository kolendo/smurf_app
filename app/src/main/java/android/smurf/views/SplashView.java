package android.smurf.views;

import eu.inloop.viewmodel.IView;

/**
 * @author Wojtek Kolendo
 */

public interface SplashView extends IView {

    /**
     * Open login activity
     */
    void startLoginActivity();

    /**
     * Open main activity
     */
    void startMainActivity();


    /**
     * Checks if location permission was granted
     */
    void checkLocationPermission();

    /**
     * Shows dialog with information about location permission
     */
    void showLocationPermissionRequiredDialog();
}
