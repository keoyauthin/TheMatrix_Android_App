package com.derdoktor667.dev.thematrix.fragments.facebook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.derdoktor667.dev.thematrix.R;

public class LoggedIn extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.id.facebook_logged_in_layout,
                container, false);
        return view;
    }
}
