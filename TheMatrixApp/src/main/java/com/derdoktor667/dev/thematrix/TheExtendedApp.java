/**
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

import android.app.Application;
import android.util.Log;

import com.derdoktor667.dev.thematrix.utils.Constants;
import com.derdoktor667.dev.thematrix.utils.PreferenceHelper;

public class TheExtendedApp extends Application {

    @Override
    public void onCreate() {

        Log.d(Constants.TAG, "Setting workaround for AsyncTask...");
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * Set Debug level based on preference
         */
        if (PreferenceHelper.getDebugEnabled(this)) {
            Constants.DEBUG = true;
            Log.d(Constants.TAG, "Debug set to true by preference!");
        } else {
            Constants.DEBUG = false;
        }
        super.onCreate();
    }
}
