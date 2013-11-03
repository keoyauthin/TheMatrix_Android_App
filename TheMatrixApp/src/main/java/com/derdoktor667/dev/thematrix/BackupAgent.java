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

package com.derdoktor667.dev.thematrix;

import android.app.backup.BackupAgentHelper;
import android.app.backup.SharedPreferencesBackupHelper;

/**
 * The backup agent for TheMatrix. See the developer guide documentation for <a
 * href="http://developer.android.com/guide/topics/data/backup.html">Data
 * Backup</a> for details.
 */

public class BackupAgent extends BackupAgentHelper {

    private static final String PREFS_BACKUP_KEY = "preferences";

    /* Allocate a helper and add it to the backup agent */
    @Override
    public void onCreate() {
        /* Compute the default preferences filename. */
        String defaultPrefsFilename = getPackageName() + "_settings";
        addHelper(PREFS_BACKUP_KEY, new SharedPreferencesBackupHelper(this, defaultPrefsFilename));
    }
}
