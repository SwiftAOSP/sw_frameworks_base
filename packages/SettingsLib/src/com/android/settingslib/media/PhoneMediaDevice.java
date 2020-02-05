/*
 * Copyright 2018 The Android Open Source Project
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
package com.android.settingslib.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2Manager;

import com.android.settingslib.R;
import com.android.settingslib.bluetooth.BluetoothUtils;

/**
 * PhoneMediaDevice extends MediaDevice to represents Phone device.
 */
public class PhoneMediaDevice extends MediaDevice {

    private static final String TAG = "PhoneMediaDevice";

    public static final String ID = "phone_media_device_id_1";

    private String mSummary = "";

    PhoneMediaDevice(Context context, MediaRouter2Manager routerManager, MediaRoute2Info info,
            String packageName) {
        super(context, MediaDeviceType.TYPE_PHONE_DEVICE, routerManager, info, packageName);

        initDeviceRecord();
    }

    @Override
    public String getName() {
        return mContext.getString(R.string.media_transfer_this_device_name);
    }

    @Override
    public String getSummary() {
        return mSummary;
    }

    @Override
    public Drawable getIcon() {
        return BluetoothUtils.buildBtRainbowDrawable(mContext,
                mContext.getDrawable(R.drawable.ic_smartphone), getId().hashCode());
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    /**
     * According current active device is {@link PhoneMediaDevice} or not to update summary.
     */
    public void updateSummary(boolean isActive) {
        mSummary = isActive
                ? mContext.getString(R.string.bluetooth_active_no_battery_level)
                : "";
    }
}
