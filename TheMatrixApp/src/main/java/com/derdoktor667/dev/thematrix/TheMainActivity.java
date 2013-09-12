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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.derdoktor667.dev.thematrix.fragments.DropboxFragment;
import com.derdoktor667.dev.thematrix.fragments.facebook.FacebookFragment;
import com.derdoktor667.dev.thematrix.fragments.GoogleDriveFragment;
import com.derdoktor667.dev.thematrix.fragments.GooglePlusFragment;
import com.derdoktor667.dev.thematrix.fragments.OverviewFragment;
import com.derdoktor667.dev.thematrix.fragments.TwitterFragment;

public class TheMainActivity extends ActionBarActivity {

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

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mNavigationTitles));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable SupportActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        );

        /** ...can´t get it to run with support.compat.v7
        {
             public void onDrawerClosed(View view) {
             getActionBar().setTitle(mTitle);
             invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
             }

             public void onDrawerOpened(View drawerView) {
             getActionBar().setTitle(mDrawerTitle);
             invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
             }
        } */

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    // ...draw the Main Menu into the ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ab_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    // ...give the Main Menu some actions
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.ab_action_settings:
                // ...open the Main Settings
                return true;

            case R.id.ab_action_about:
                // ...get the Layout for this Action
                LayoutInflater li = LayoutInflater.from(this);
                View view = li.inflate(R.layout.ab_action_layout_about, null);

                // ...fill in the version...
                TextView tv = null;

                if (view != null) {
                    tv = (TextView) view.findViewById(R.id.version);
                    }

                    PackageManager pm = getPackageManager();

                try {
                    PackageInfo pi;
                    //noinspection ConstantConditions
                    pi = pm.getPackageInfo(getApplicationContext().getPackageName(), 0);

                if (tv != null) tv.setText(pi.versionName);
            }

                catch (PackageManager.NameNotFoundException ignored) {
                // ...insert empty line ^^
                }

            AlertDialog.Builder p = new AlertDialog.Builder(this).setView(view);

            final AlertDialog alrt = p.create();

            alrt.setIcon(R.drawable.ic_launcher);
            alrt.setTitle(getString(R.string.section_about));

            alrt.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.about_website),

            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    Uri uri = Uri.parse(getString(R.string.thematrix_http_url));
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    }
                }
            );

            alrt.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int whichButton) {
                 // ....empty again
                 }
            }
        );

        alrt.show();
        return true;

        case R.id.ab_action_exit:
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            
            // Locate Position
            switch (position) {

                case 0:
                    OverviewFragment overviewFragment = new OverviewFragment();
                    ft.replace(R.id.main_content, overviewFragment);
                    break;

                case 1:
                    DropboxFragment dropboxFragment = new DropboxFragment();
                    ft.replace(R.id.main_content, dropboxFragment);
                    break;

                case 2:
                    GoogleDriveFragment googleDriveFragment = new GoogleDriveFragment();
                    ft.replace(R.id.main_content, googleDriveFragment);
                    break;

                case 3:
                    FacebookFragment facebookFragment = new FacebookFragment();
                    ft.replace(R.id.main_content, facebookFragment);
                    break;

                case 4:
                    GooglePlusFragment googlePlusFragment = new GooglePlusFragment();
                    ft.replace(R.id.main_content, googlePlusFragment);
                    break;

                case 5:
                    TwitterFragment twitterFragment = new TwitterFragment();
                    ft.replace(R.id.main_content, twitterFragment);
                    break;
            }

            ft.commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mNavigationTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    // When using the ActionBarDrawerToggle, you must call it during onPostCreate() and onConfigurationChanged()
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}