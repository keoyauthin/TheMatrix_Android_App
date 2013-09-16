package com.derdoktor667.dev.thematrix.fragments.facebook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.derdoktor667.dev.thematrix.R;

/**
 * Created by derdoktor on 16.09.13.
 */
public class TheFacebookAuthedFragment extends Fragment {

    private static final String TAG = "TheFacebookAuthedFragment";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.facebook_fragment_authed_layout,
                container, false);
        return view;
    }
}
