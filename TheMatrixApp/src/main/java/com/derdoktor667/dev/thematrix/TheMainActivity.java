/*
 * Copyright 2013 Wastl Kraus <derdoktor667@gmail.com>
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.derdoktor667.dev.thematrix;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.derdoktor667.dev.thematrix.utils.HelpUtils;

public class TheMainActivity extends ActionBarActivity {

    // ...introduce the Fragments
    private static final int OVERVIEW = 0;
    private static final int DROPBOX = 1;
    private static final int GOOGLEDRIVE = 2;
    private static final int FACEBOOK = 3;
    private static final int GOOGLEPLUS = 4;
    private static final int TWITTER = 5;
    private static final int SETTINGS = 6;
    private static final int FRAGMENT_COUNT = SETTINGS + 1;

    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];

    // ...set up the needed Stuff for the Side Navigation Drawer
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mNavigationTitles;

    /* ...start with the usual onCreate Boilerplate */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.thematrix_main_layout);

        mTitle = mDrawerTitle;
        mDrawerTitle = getTitle();
        mNavigationTitles = getResources().getStringArray(R.array.navigation_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        /*
         * set a custom shadow that overlays the main content when the drawer
         * opens
         */
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        /* set up the drawer's list view with items and click listener */
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item,
                mNavigationTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        /*
         * enable SupportActionBar app icon to behave as action and toggle the
         * drawer
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */

                R.string.drawer_open, /*
                                       * "open drawer" description for
                                       * accessibility
                                       */

                R.string.drawer_close /*
                                       * "close drawer" description for
                                       * accessibility
                                       */
                )

                /* ...set the matching Titels by the array Names */
                {
                    public void onDrawerClosed(View view) {
                        getSupportActionBar().setTitle(mTitle);
                        supportInvalidateOptionsMenu();
                    }

                    public void onDrawerOpened(View drawerView) {
                        getSupportActionBar().setTitle(mDrawerTitle);
                        supportInvalidateOptionsMenu();
                    }
                };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        /* ...the Fragments for the Drawer */
        FragmentManager fm = getSupportFragmentManager();

        fragments[OVERVIEW] = fm.findFragmentById(R.id.overviewFragment);
        fragments[DROPBOX] = fm.findFragmentById(R.id.dropboxFragment);
        fragments[GOOGLEDRIVE] = fm.findFragmentById(R.id.goodriveFragment);
        fragments[FACEBOOK] = fm.findFragmentById(R.id.facebookFragment);
        fragments[GOOGLEPLUS] = fm.findFragmentById(R.id.gooplusFragment);
        fragments[TWITTER] = fm.findFragmentById(R.id.twitterFragment);
        fragments[SETTINGS] = fm.findFragmentById(R.id.settingsFragment);

        FragmentTransaction transaction = fm.beginTransaction();

        for (Fragment fragment : fragments) {
            transaction.hide(fragment);
        }
        transaction.commit();
        showOverviewFragment();
    }

    private void showOverviewFragment() {
        showFragment(OVERVIEW);
    }

    private void showDropboxFragment() {
        showFragment(DROPBOX);
    }

    private void showGoogleDriveFragment() {
        showFragment(GOOGLEDRIVE);
    }

    private void showFacebookFragment() {
        showFragment(FACEBOOK);
    }

    private void showGooglePlusFragment() {
        showFragment(GOOGLEPLUS);
    }

    private void showTwitterFragment() {
        showFragment(TWITTER);
    }

    private void showSettingsFragment() {
        showFragment(SETTINGS);
    }

    /* ...draw the Main Menu into the ActionBar */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* ...some Placeholder for now */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /* ...give the Main Menu some actions */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.ab_action_refresh:
                return true;

            case R.id.ab_action_settings:
                showSettingsFragment();
                return true;

            case R.id.ab_action_about:
                HelpUtils.showAboutDialog(this);
                return true;

            case R.id.ab_action_exit:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFragment(int fragmentIndex) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            if (i == fragmentIndex) {
                transaction.show(fragments[i]);
            } else {
                transaction.hide(fragments[i]);
            }
        }
        transaction.commit();
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {

            /* Locate Position */
            switch (position) {

                case 0:
                    showOverviewFragment();
                    break;

                case 1:
                    showDropboxFragment();
                    break;

                case 2:
                    showGoogleDriveFragment();
                    break;

                case 3:
                    showFacebookFragment();
                    break;

                case 4:
                    showGooglePlusFragment();
                    break;

                case 5:
                    showTwitterFragment();
                    break;
            }
            setTitle(mNavigationTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
            mDrawerList.setItemChecked(position, true);
        }
    }

    /* ...to change the Titels on click events */
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /* ...call during onPostCreate() and onConfigurationChanged() */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        /* Sync the toggle state after onRestoreInstanceState has occurred. */
        mDrawerToggle.syncState();
    }

    /* ...keep the NavigationDrawer up-to-date */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    // ...add the onFoo Stuff
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
