/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Wastl "derdoktor667" Kraus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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

import com.derdoktor667.dev.thematrix.utils.AboutDialog;
import com.derdoktor667.dev.thematrix.utils.Log;
import com.google.analytics.tracking.android.EasyTracker;

public class TheMainActivity extends ActionBarActivity {

    /**
     * ...introduce the Fragments
     */
    private static final int OVERVIEW = 0;
    private static final int DROPBOX = 1;
    private static final int GOOGLEDRIVE = 2;
    private static final int FACEBOOK = 3;
    private static final int GOOGLEPLUS = 4;
    private static final int TWITTER = 5;
    private static final int FRAGMENT_COUNT = TWITTER + 1;
    private final String TAG = TheMainActivity.class.getSimpleName();
    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];
    /**
     * ...set up the Navigation Drawer
     */
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mNavigationTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.thematrix_main_layout);

        mTitle = mDrawerTitle;
        mDrawerTitle = getTitle();
        mNavigationTitles = getResources().getStringArray(R.array.navigation_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        /**
         * set a custom shadow that overlays the main content when the drawer
         * opens
         */
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        /**
         * set up the drawer's list view with items and click listener
         */
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item,
                mNavigationTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        /**
         * enable SupportActionBar app icon to behave as action and toggle the
         * drawer
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /**
         * make the Navigation Drawer
         */
        mDrawerToggle = new ActionBarDrawerToggle(

                /**
                 * host Activity
                 */
                this,

                /**
                 * DrawerLayout object
                 */
                mDrawerLayout,

                /**
                 * nav drawer image to replace 'Up' caret
                 */
                R.drawable.ic_drawer,

                /**
                 * "open drawer" description for accessibility
                 */
                R.string.drawer_open,

                /**
                 * "open drawer" description for accessibility
                 */
                R.string.drawer_close
                )

                /**
                 * ...set the matching Titels by the array Names
                 */
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

        /**
         * ...the Fragments for the Drawer
         */
        FragmentManager fm = getSupportFragmentManager();

        fragments[OVERVIEW] = fm.findFragmentById(R.id.overviewFragment);
        fragments[DROPBOX] = fm.findFragmentById(R.id.dropboxFragment);
        fragments[GOOGLEDRIVE] = fm.findFragmentById(R.id.goodriveFragment);
        fragments[FACEBOOK] = fm.findFragmentById(R.id.facebookFragment);
        fragments[GOOGLEPLUS] = fm.findFragmentById(R.id.gooplusFragment);
        fragments[TWITTER] = fm.findFragmentById(R.id.twitterFragment);

        FragmentTransaction transaction = fm.beginTransaction();

        for (Fragment fragment : fragments) {
            transaction.hide(fragment);
        }
        transaction.commit();
        showOverviewFragment();
        EasyTracker.getInstance(this);
    }

    private void showOverviewFragment() {
        Log.i(TAG, "show Overview Fragment");
        showFragment(OVERVIEW);
    }

    private void showDropboxFragment() {
        Log.i(TAG, "show Dropbox Fragment");
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

    /**
     * ...draw the Main Menu into the ActionBar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * ...some Placeholder for now
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * ...give the Main Menu some actions
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.ab_action_refresh:
                return true;

            case R.id.ab_action_settings:
                return true;

            case R.id.ab_action_about:
                AboutDialog.showAboutDialog(this);
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

    /**
     * ...to change the Titels on click events
     */
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * ...call during onPostCreate() and onConfigurationChanged()
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        /**
         * Sync the toggle state after onRestoreInstanceState has occurred.
         */
        mDrawerToggle.syncState();
    }

    /**
     * ...keep the NavigationDrawer up-to-date
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        /**
         * Pass any configuration change to the drawer toggles
         */
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onStart() {
        super.onStart();
        mDrawerLayout.openDrawer(mDrawerList);
        EasyTracker.getInstance(this).activityStart(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }

    /**
     * The click listener for ListView in the navigation drawer
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {

            /**
             * Locate Position
             */
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
}
