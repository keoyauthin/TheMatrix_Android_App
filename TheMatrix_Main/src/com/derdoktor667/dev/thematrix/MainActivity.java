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
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SherlockActivity {

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            if (position == 0) {
                // Aktion
            } else if (position == 1) {
                // Aktion
            } else if (position == 2) {
                // Aktion
            } else if (position == 3) {
                // Aktion
            } else if (position == 4) {
                // Aktion
            } else if (position == 5) {
                // Aktion
            }

            mDrawerList.setItemChecked(position, true);
            mTitle = drawerTitles[position];
            getSupportActionBar().setTitle(mTitle);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle;
    private String[] drawerTitles;

    // private int[] drawerIcons;

    private String[] drawerSubtitles;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = getTitle();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        // Hole die Titel aus einem Array aus der strings.xml
        drawerTitles = getResources()
                .getStringArray(R.array.drawerTitles_array);

        // Hole die Untertitel aus einem Array aus der strings.xml
        drawerSubtitles = getResources().getStringArray(
                R.array.drawerSubtitles_array);

        // Setzt die Icons zu den Einträgen
        // drawerIcons = new int[] {android.R.drawable.ic_menu_info_details,
        // android.R.drawable.ic_menu_edit,
        // android.R.drawable.ic_menu_delete};

        // Erstellt den neuen MenuAdapter aus der Klasse MenuListAdapter
        MenuListAdapter mMenuAdapter = new MenuListAdapter(this, drawerTitles,
                drawerSubtitles, null); // drawerIcons);
        mDrawerList.setAdapter(mMenuAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Bereitet die ActionBar auf den Navigation Drawer vor
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Fügt den Navigation Drawer zur ActionBar hinzu
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(R.string.app_name);
                supportInvalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    // Fügt das Menü hinzu / ActionBar Einträge
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // öffnet und schließt den Navigation Drawer bei Klick auf den Titel/das
        // Icon in der ActionBar
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }

        // Gibt den ActionBar-Buttons Funktionen
        switch (item.getItemId()) {

            case R.id.get_settings_mmenu:
                // Tu' etwas!
                return true;

            case R.id.get_about_mmenu:
                LayoutInflater li = LayoutInflater.from(this);
                View view = li.inflate(R.layout.intent_about, null);

                // Fill in the version...
                TextView tv = (TextView) view.findViewById(R.id.version);
                PackageManager pm = getPackageManager();

                try {

                    PackageInfo pi = pm.getPackageInfo(getApplicationContext()
                            .getPackageName(), 0);
                    tv.setText(pi.versionName);

                } catch (Exception e) {

                }

                Builder p = new AlertDialog.Builder(this).setView(view);
                final AlertDialog alrt = p.create();
                alrt.setIcon(R.drawable.about_icon);
                alrt.setTitle(getString(R.string.about_title));
                alrt.setButton(DialogInterface.BUTTON_NEUTRAL,
                        getString(R.string.about_website),
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                Uri uri = Uri
                                        .parse("http://wir-sind-die-matrix.de");
                                startActivity(new Intent(Intent.ACTION_VIEW, uri));

                            }
                        });

                alrt.setButton(DialogInterface.BUTTON_NEGATIVE,
                        getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                            }
                        });

                alrt.show();

                return false;

            case R.id.take_exit_mmenu:
                finish();
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    // Versteckt die ActionBar-Einträge, sobald der Drawer ausgefahren is
    // @Override
    // public boolean onPrepareOptionsMenu(Menu menu) {
    // boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
    // menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
    // return super.onPrepareOptionsMenu(menu);
    // }
}