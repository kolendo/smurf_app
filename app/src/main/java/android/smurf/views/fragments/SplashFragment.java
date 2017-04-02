package android.smurf.views.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.smurf.R;
import android.smurf.viewmodels.SplashViewModel;
import android.smurf.views.SplashView;
import android.smurf.views.activities.MainActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import software.rsquared.permissiontools.OnPermissionResultTask;
import software.rsquared.permissiontools.Permissions;

/**
 * @author Wojtek Kolendo
 */

public class SplashFragment extends ViewModelBaseFragment<SplashView, SplashViewModel> implements SplashView {


    private AlertDialog locationPermissionDialog;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public void startLoginActivity() {
//        Intent intent = new Intent(getContext(), LoginActivity.class);
//        startActivity(intent);
//        getActivity().finish();
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void checkLocationPermission() {
        Permissions.checkPermission(this, new OnPermissionResultTask() {
            @Override
            public void onPermissionGranted(@NonNull String[] permissions) throws SecurityException {
                getViewModel().markLocationGranted();
            }
            @Override
            public void onPermissionDenied(@NonNull String[] grantedPermissions, @NonNull String[] deniedPermissions) {
                getViewModel().markLocationDenied();
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Permissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void showLocationPermissionRequiredDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.SmurfTheme_Dialog);
        dialog.setCancelable(false);
        dialog.setMessage(R.string.splash_location_required);
        dialog.setPositiveButton(R.string.all_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                locationPermissionDialog = null;
                getViewModel().requestLocationPermission();
            }
        });
        dialog.setNeutralButton(R.string.all_close_app, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                locationPermissionDialog = null;
                getActivity().finish();
            }
        });
        locationPermissionDialog = dialog.show();
    }
}
