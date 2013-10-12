
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

package com.derdoktor667.dev.thematrix.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.*;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.derdoktor667.dev.thematrix.R;

public class TheUtils {

    public static void showAboutDialog(FragmentActivity activity) {

        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment prev = fm.findFragmentByTag("about_popup_layout");
        if (prev != null) {
            ft.remove(prev);
        }

        ft.addToBackStack(null);
        new AboutDialog().show(ft, "about_popup_layout");
    }

    public static class AboutDialog extends DialogFragment {

        private static final String VERSION_UNAVAILABLE = "N/A";

        public AboutDialog() {

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            /* Get app version */
            PackageManager pm = getActivity().getPackageManager();
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

            /* Build the about body view and append the link to see OSS licenses */
            LayoutInflater layoutInflater = getActivity().getLayoutInflater();
            View rootView = layoutInflater.inflate(R.layout.about_popup_layout, null);
            TextView nameAndVersionView = null;

            if (rootView != null) {
                nameAndVersionView = (TextView) rootView.findViewById(
                        R.id.app_name_and_version);
            }

            if (nameAndVersionView != null) {
                nameAndVersionView.setText(Html.fromHtml(getString(R.string.app_name_and_version,
                        versionName)));
            }

            TextView aboutBodyView = null;
            if (rootView != null) {

                aboutBodyView = (TextView) rootView.findViewById(R.id.about_body);
            }

            if (aboutBodyView != null) {

                aboutBodyView.setText(Html.fromHtml(getString(R.string.about_body)));
            }

            if (aboutBodyView != null) {

                aboutBodyView.setMovementMethod(new LinkMovementMethod());
            }

            return new AlertDialog.Builder(getActivity())

                    .setView(rootView)
                    .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    }
                    ).create();
        }
    }
}
