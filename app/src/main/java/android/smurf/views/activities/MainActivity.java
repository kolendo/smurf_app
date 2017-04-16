package android.smurf.views.activities;


import android.content.Context;
import android.os.Bundle;
import android.smurf.R;
import android.smurf.views.fragments.NearbySlopesFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;

/**
 * @author Wojtek Kolendo
 */

public class MainActivity extends ViewModelBaseEmptyActivity {

    ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(new SlopesPagerAdapter(getSupportFragmentManager()));
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class SlopesPagerAdapter extends FragmentPagerAdapter {

        public SlopesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return NearbySlopesFragment.newInstance();
                case 1:
                    return NearbySlopesFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.main_nearby);
                case 1:
                    return getString(R.string.main_favourites);
            }
            return null;
        }
    }

}
