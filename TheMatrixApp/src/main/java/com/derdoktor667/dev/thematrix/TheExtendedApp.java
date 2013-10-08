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

package com.derdoktor667.dev.thematrix;

import android.app.Application;

import com.derdoktor667.dev.thematrix.utils.LogUtils;

import static com.derdoktor667.dev.thematrix.utils.LogUtils.LOGD;

public class TheExtendedApp extends Application {

    private static final String TAG = LogUtils.makeLogTag(TheExtendedApp.class);

    public void onCreate() {
        LOGD(TAG, "onCreate; created TheExtendedApp (TheMatrix)");
        super.onCreate();
    }
}