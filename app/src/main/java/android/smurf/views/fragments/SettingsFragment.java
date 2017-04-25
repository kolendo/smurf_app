package android.smurf.views.fragments;

import android.os.Bundle;
import android.smurf.R;
import android.smurf.SmurfApplication;
import android.support.annotation.Nullable;
import android.support.v7.preference.ListPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompatDividers;

/**
 * @author Wojtek Kolendo
 */

public class SettingsFragment extends PreferenceFragmentCompatDividers {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setDividerPreferences(DIVIDER_DEFAULT);
        try {
            return super.onCreateView(inflater, container, savedInstanceState);
        } finally {
             setDividerPreferences(DIVIDER_NONE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ListPreference radiusPreference = (ListPreference) getPreferenceScreen()
                .findPreference(SmurfApplication.getContext().getString(R.string.settings_preference_search_radius));

    }

    private void showRadiusDialog() {

    }
}
