package android.smurf.views.fragments;

import android.os.Bundle;
import android.smurf.R;
import android.smurf.SmurfApplication;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

/**
 * @author Wojtek Kolendo
 */

public class SettingsFragment extends PreferenceFragmentCompat {


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Preference radiusPreference = getPreferenceScreen()
                .findPreference(SmurfApplication.getContext().getString(R.string.settings_preference_search_radius));

        radiusPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showRadiusDialog();
                return true;
            }
        });
    }

    private void showRadiusDialog() {

    }
}
