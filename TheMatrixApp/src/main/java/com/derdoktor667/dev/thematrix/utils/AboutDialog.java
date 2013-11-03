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

package com.derdoktor667.dev.thematrix.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.derdoktor667.dev.thematrix.R;

public class AboutDialog extends DialogFragment {

    public static void showAboutDialog(ActionBarActivity activity) {

        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment prev = fm.findFragmentByTag("about_popup_layout");
        if (prev != null) {
            ft.remove(prev);
        }

        ft.addToBackStack(null);
        new AboutDialog().show(ft, "about_popup_layout");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /**
         * Get Application Version
         */
        PackageManager pm = getActivity().getPackageManager();
        String packageName = getActivity().getPackageName();
        TextView nameAndVersionView = null;
        TextView aboutBodyView = null;
        String versionName = null;
        PackageInfo info = null;

        try {
            if (pm != null) {
                info = pm.getPackageInfo(packageName, 0);
            }

            if (info != null) {
                versionName = info.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            versionName = Constants.VERSION_UNAVAILABLE;
        }

        /**
         * Build the aboutTextView aboutBodyView = null; body view and append
         * the link to see OSS licenses
         */
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View rootView = layoutInflater.inflate(R.layout.about_popup_layout, null);

        if (rootView != null) {
            nameAndVersionView = (TextView) rootView.findViewById(R.id.app_name_and_version);
        }

        if (rootView != null) {
            aboutBodyView = (TextView) rootView.findViewById(R.id.about_body);
        }

        if (nameAndVersionView != null) {
            nameAndVersionView.setText(Html.fromHtml(getString(R.string.app_name_and_version,
                    versionName)));
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
