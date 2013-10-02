package com.derdoktor667.dev.thematrix.fragments.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import com.derdoktor667.dev.thematrix.R;

public class SettingsFragment extends PreferencesListFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener,
        PreferencesListFragment.OnPreferenceAttachedListener {

    public static final String SHARED_PREFS_NAME = "settings";

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.setSharedPreferencesName(SHARED_PREFS_NAME);
        addPreferencesFromResource(R.xml.settings);
        preferenceManager.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    }

    @Override
    public void onPreferenceAttached(PreferenceScreen root, int xmlId) {

    }
}

