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

    public static final int DBX_CHOOSER_REQUEST = 1;
    private static final String APP_KEY = String.valueOf(R.string.dropbox_chooser_key_string);

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
                mChooser.forResultType(DbxChooser.ResultType.DIRECT_LINK)
                        .launch(DropboxFragment.this, DBX_CHOOSER_REQUEST);
            }
        }
                );
        return view;
    }
}
