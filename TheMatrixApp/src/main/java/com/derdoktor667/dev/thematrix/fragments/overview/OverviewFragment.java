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

package com.derdoktor667.dev.thematrix.fragments.overview;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.derdoktor667.dev.thematrix.R;

public class OverviewFragment extends Fragment {

    private static final String VERSION_UNAVAILABLE = "N/A";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_popup_layout, container, false);

        PackageManager pm;
        pm = getActivity().getPackageManager();
        String packageName = getActivity().getPackageName();
        String versionName = null;

        try {
            PackageInfo info = null;

            if (pm != null) {
                info = pm.getPackageInfo(packageName, 0);
            }

            if (info != null) {
                versionName = info.versionName;
            }

        } catch (PackageManager.NameNotFoundException e) {
            versionName = VERSION_UNAVAILABLE;
        }

        TextView nameAndVersionView = null;

        if (view != null) {
            nameAndVersionView = (TextView) view.findViewById(R.id.app_name_and_version);
        }

        if (nameAndVersionView != null) {
            nameAndVersionView.setText(Html.fromHtml(getString(R.string.app_name_and_version,
                    versionName)));
        }

        TextView aboutBodyView = null;

        if (view != null) {
            aboutBodyView = (TextView) view.findViewById(R.id.about_body);
        }

        if (aboutBodyView != null) {
            aboutBodyView.setText(Html.fromHtml(getString(R.string.about_body)));
        }

        if (aboutBodyView != null) {
            aboutBodyView.setMovementMethod(new LinkMovementMethod());
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getView();
    }
}
