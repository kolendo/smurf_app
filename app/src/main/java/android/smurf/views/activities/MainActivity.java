package android.smurf.views.activities;


import android.os.Bundle;
import android.smurf.R;
import android.smurf.views.fragments.NearbySlopesFragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;

/**
 * @author Wojtek Kolendo
 */

public class MainActivity extends ViewModelBaseEmptyActivity {

    private final int CONTAINER_FRAME_LAYOUT = R.id.fragment_frame;

    BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_frame, NearbySlopesFragment.newInstance())
                    .commit();
        }

        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.action_nearby:
                        setTitle(R.string.main_ski_slopes);
                        ft.replace(CONTAINER_FRAME_LAYOUT, NearbySlopesFragment.newInstance());
                        ft.commit();
                        break;
                    case R.id.action_favourites:
                        setTitle(R.string.main_ski_slopes);
                        ft.replace(CONTAINER_FRAME_LAYOUT, NearbySlopesFragment.newInstance());
                        ft.commit();
                        break;
                    case R.id.action_settings:
                        setTitle(R.string.main_settings);
                        ft.replace(CONTAINER_FRAME_LAYOUT, NearbySlopesFragment.newInstance());
                        ft.commit();
                        break;
                }
                return true;
            }
        });
    }

}
