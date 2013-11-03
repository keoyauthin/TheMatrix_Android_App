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

import android.content.Context;
import android.content.SharedPreferences;

import com.derdoktor667.dev.thematrix.R;

public class PreferenceHelper {

    public static boolean getDebugEnabled(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_NAME,
                Context.MODE_PRIVATE);
        return prefs.getBoolean(context.getString(R.string.pref_enable_debug_key),
                Boolean.parseBoolean(context.getString(R.string.pref_enable_debug_def)));
    }
}
