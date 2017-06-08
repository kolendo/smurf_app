package android.smurf.views.activities;

import android.os.Bundle;
import android.smurf.R;
import android.smurf.models.SkiSlope;
import android.smurf.views.fragments.SkiSlopeDetailsFragment;

import eu.inloop.viewmodel.base.ViewModelBaseActivity;

/**
 * @author Wojtek Kolendo
 */

public class SkiSlopeDetailsActivity extends ViewModelBaseActivity {

    public static final String SKI_SLOPE_EXTRA = "ski_slope_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_only);
        SkiSlope skiSlope = (SkiSlope) getIntent().getSerializableExtra(SKI_SLOPE_EXTRA);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_frame, SkiSlopeDetailsFragment.newInstance(skiSlope.getId())).commit();
        }
    }

}
