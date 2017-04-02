package android.smurf.views.activities;

import android.os.Bundle;
import android.smurf.R;
import android.smurf.views.fragments.SplashFragment;

import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;


/**
 * @author Wojtek Kolendo
 */

public class SplashActivity extends ViewModelBaseEmptyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_only);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_frame, SplashFragment.newInstance()).commit();
        }
    }
}
