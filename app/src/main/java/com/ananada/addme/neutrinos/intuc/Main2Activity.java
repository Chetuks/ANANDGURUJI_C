package com.ananada.addme.neutrinos.intuc;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import static android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class Main2Activity extends AppCompatActivity {
    private ActionBar toolbar;
    boolean doubleBackToExitPressedOnce = false;
    BottomNavigationView navigation;
    private String FRAGMENT_HOME = "Home";
    private String FRAGMENT_OTHER = "Other";
    private int mMenuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = getSupportActionBar();
        //mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       /* navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                mMenuId = item.getItemId();
                for (int i = 0; i < navigation.getMenu().size(); i++) {
                    MenuItem menuItem = navigation.getMenu().getItem(i);
                    boolean isChecked = menuItem.getItemId() == item.getItemId();
                    menuItem.setChecked(isChecked);
                }

                switch (item.getItemId()) {
                    case R.id.navigation_home: {
                        toolbar.setTitle("Home");
                      //  fragment = new DashBoardActivity();
                        //loadFragment(fragment, FRAGMENT_HOME);
                    }
                    break;
                    //  return true;
                    case R.id.navigation_dashboard: {
                        toolbar.setTitle("Dashboard");
                        fragment = new BlankFragment();
                        loadFragment(fragment, FRAGMENT_OTHER);
                    }
                    break;
                    // return true;
                    case R.id.navigation_notifications: {
                        toolbar.setTitle("Notifications");
                       fragment = new NearbyActivity();
                        loadFragment(fragment, FRAGMENT_OTHER);
                    }
                    break;
                    //return true;
                }
                return true;
            }
        });*/
        navigation.getMenu().findItem(R.id.navigation_home).setChecked(true);
        toolbar.setTitle("INTUC");
        loadFragment(new DashBoardActivity(), FRAGMENT_HOME);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            mMenuId = item.getItemId();
            for (int i = 0; i < navigation.getMenu().size(); i++) {
                MenuItem menuItem = navigation.getMenu().getItem(i);
                boolean isChecked = menuItem.getItemId() == item.getItemId();
                menuItem.setChecked(isChecked);
            }
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    toolbar.setTitle("Home");
                    fragment = new DashBoardActivity();
                    loadFragment(fragment, FRAGMENT_HOME);
                }
                break;
                //  return true;
                case R.id.navigation_dashboard: {
                    toolbar.setTitle("Dashboard");
                    fragment = new BlankFragment();
                    loadFragment(fragment, FRAGMENT_OTHER);
                }
                break;
                // return true;
                case R.id.navigation_notifications: {
                    toolbar.setTitle("Notifications");
                    fragment = new NearbyActivity();
                    loadFragment(fragment, FRAGMENT_OTHER);
                }
                break;
                //return true;
            }
            return true;
        }
    };


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void loadFragment(Fragment fragment, String name) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        //transaction.addToBackStack(null);
        // transaction.commit();

        final FragmentManager fragmentManager = getFragmentManager();
        // 1. Know how many fragments there are in the stack
        final int count = fragmentManager.getBackStackEntryCount();
        // 2. If the fragment is **not** "home type", save it to the stack
       /* if (name.equals(FRAGMENT_OTHER)) {
            transaction.addToBackStack(name);
        }*/
        // Commit !
        transaction.commit();
        // 3. After the commit, if the fragment is not an "home type" the back stack is changed, triggering the
        // OnBackStackChanged callback
      /*  fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                // If the stack decreases it means I clicked the back button
                if (fragmentManager.getBackStackEntryCount() <= count) {
                    // pop all the fragment and remove the listener
                    fragmentManager.popBackStack(FRAGMENT_OTHER, POP_BACK_STACK_INCLUSIVE);
                    fragmentManager.removeOnBackStackChangedListener(this);
                    // set the home button selected

                }
            }
        });*/
    }
}
