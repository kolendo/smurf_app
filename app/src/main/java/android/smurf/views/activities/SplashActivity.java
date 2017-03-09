package android.smurf.views.activities;

import android.os.Bundle;
import android.smurf.R;
import android.support.v7.app.AppCompatActivity;


/**
 * @author Wojtek Kolendo
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction().add(R.id.fragment_frame, SplashFragment.newInstance()).commit();
        }
    }

}
