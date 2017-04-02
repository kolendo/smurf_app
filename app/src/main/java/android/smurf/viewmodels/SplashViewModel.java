package android.smurf.viewmodels;

import android.os.Handler;
import android.smurf.views.SplashView;
import android.support.annotation.NonNull;

import eu.inloop.viewmodel.AbstractViewModel;
import software.rsquared.restapi.exceptions.RequestException;

/**
 * @author Wojtek Kolendo
 */

public class SplashViewModel extends AbstractViewModel<SplashView> {

    private Handler handler = new Handler();
    private boolean splashDelayReached;
    private boolean logged;
    private boolean locationGranted;

    private Runnable delayedRunnable = new Runnable() {
        @Override
        public void run() {
            splashDelayReached = true;
            closeSplash();
        }
    };

    @Override
    public void onBindView(@NonNull SplashView view) {
        super.onBindView(view);
//        UserService service = UserService.getInstance();
//        if (NetworkUtils.canTransferData() && service.canAutoLogin()) {
//            service.login(this);
//        } else {
//            logged = true;
//        }
//        if (NetworkUtils.canTransferData()) {
//            view.checkApiVersion();
//        } else {
//            appVersionChecked = true;
//        }
        requestLocationPermission();

        handler.postDelayed(delayedRunnable, 1000);
    }

    public void onLogInSuccess() {
        logged = true;
        closeSplash();
    }

    public void onLogInFailed(RequestException error) {
//        UserService.getInstance().logout();
        logged = true;
        closeSplash();
    }

    public void markLocationGranted() {
        locationGranted = true;
        closeSplash();
    }

    /**
     * This method decides that splash screen should be closed or not and which screen should be opened.
     */
    private void closeSplash() {
        if (getView() != null && splashDelayReached && locationGranted) {
//            if (UserService.getInstance().isLogged()) {
                    getView().startMainActivity();
//                }
//            } else {
//                getView().startLoginActivity();
        }
    }

    public void requestLocationPermission() {
        if (getView() != null) {
            getView().checkLocationPermission();
        }
    }

    public void markLocationDenied() {
        if (getView() != null) {
            getView().showLocationPermissionRequiredDialog();
        }
    }
}
