/*
 * Copyright (C) 2016 The Pure Nexus Project
 * Copyright (C) 2016 ABC ROM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abc.settings;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.PreferenceCategory;

import com.android.internal.logging.MetricsProto.MetricsEvent;

import com.android.settings.SettingsPreferenceFragment;

public class AbcSettings extends SettingsPreferenceFragment {

    private PreferenceCategory mLedsCategory;
    private Preference mChargingLeds;
    private Preference mNotificationLeds;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        addPreferencesFromResource(R.xml.abc_settings_main);
        PreferenceScreen prefSet = getPreferenceScreen();

        mLedsCategory = (PreferenceCategory) findPreference("abc_leds");
        mChargingLeds = (Preference) findPreference("abc_charging_light");
        mNotificationLeds = (Preference) findPreference("abc_notification_light");
        if (mChargingLeds != null
                && !getResources().getBoolean(
                        com.android.internal.R.bool.config_intrusiveBatteryLed)) {
            mLedsCategory.removePreference(mChargingLeds);
        }
        if (mNotificationLeds != null
                && !getResources().getBoolean(
                        com.android.internal.R.bool.config_intrusiveNotificationLed)) {
            mLedsCategory.removePreference(mNotificationLeds);
        }
        if (mChargingLeds == null && mNotificationLeds == null) {
            prefSet.removePreference(mLedsCategory);
        }
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.ABC;
    }
}

