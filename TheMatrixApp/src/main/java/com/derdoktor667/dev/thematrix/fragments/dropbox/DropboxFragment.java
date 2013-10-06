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

package com.derdoktor667.dev.thematrix.fragments.dropbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.derdoktor667.dev.thematrix.R;
import com.dropbox.chooser.android.DbxChooser;

public class DropboxFragment extends Fragment {

    static final String APP_KEY = "ldutdh6ixrto8yn";
    static final int DBX_CHOOSER_REQUEST = 0;

    private Button mChooserButton;
    private DbxChooser mChooser;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dropbox_fragment_layout, container, false);

        mChooser = new DbxChooser(APP_KEY);

        if (view != null) {
            mChooserButton = (Button) view.findViewById(R.id.chooser_button);
        }
        mChooserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChooser.forResultType(DbxChooser.ResultType.PREVIEW_LINK)
                        .launch(DropboxFragment.this, DBX_CHOOSER_REQUEST);
            }
        });
        return view;
    }
}