/*
 * Copyright (C) 2022 The Android Open Source Project
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

package com.android.systemui.statusbar.notification.interruption

import com.android.systemui.log.LogBuffer
import com.android.systemui.log.LogLevel.DEBUG
import com.android.systemui.log.LogLevel.INFO
import com.android.systemui.log.LogLevel.WARNING
<<<<<<< HEAD
import com.android.systemui.log.dagger.NotificationHeadsUpLog
import com.android.systemui.log.dagger.NotificationLog
import com.android.systemui.statusbar.notification.collection.NotificationEntry
=======
import com.android.systemui.log.dagger.NotificationInterruptLog
import com.android.systemui.statusbar.notification.collection.NotificationEntry
import com.android.systemui.statusbar.notification.logKey
>>>>>>> 00e5a18be27a12d55faacfe31d5e2f57c377a7f5
import javax.inject.Inject

class NotificationInterruptLogger @Inject constructor(
    @NotificationInterruptLog val buffer: LogBuffer
) {
    fun logHeadsUpFeatureChanged(useHeadsUp: Boolean) {
        buffer.log(TAG, INFO, {
            bool1 = useHeadsUp
        }, {
            "heads up is enabled=$bool1"
        })
    }

    fun logWillDismissAll() {
        buffer.log(TAG, INFO, {
        }, {
            "dismissing any existing heads up notification on disable event"
        })
    }

    fun logNoBubbleNotAllowed(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No bubble up: not allowed to bubble: $str1"
        })
    }

    fun logNoBubbleNoMetadata(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No bubble up: notification: $str1 doesn't have valid metadata"
        })
    }

    fun logNoHeadsUpFeatureDisabled() {
        buffer.log(TAG, DEBUG, {
        }, {
            "No heads up: no huns"
        })
    }

    fun logNoHeadsUpPackageSnoozed(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No alerting: snoozed package: $str1"
        })
    }

    fun logNoHeadsUpAlreadyBubbled(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No heads up: in unlocked shade where notification is shown as a bubble: $str1"
        })
    }

    fun logNoHeadsUpSuppressedByDnd(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No heads up: suppressed by DND: $str1"
        })
    }

    fun logNoHeadsUpNotImportant(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No heads up: unimportant notification: $str1"
        })
    }

    fun logNoHeadsUpNotInUse(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No heads up: not in use: $str1"
        })
    }

    fun logNoHeadsUpSuppressedBy(
        entry: NotificationEntry,
        suppressor: NotificationInterruptSuppressor
    ) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
            str2 = suppressor.name
        }, {
            "No heads up: aborted by suppressor: $str2 sbnKey=$str1"
        })
    }

    fun logHeadsUp(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "Heads up: $str1"
        })
    }

    fun logNoAlertingFilteredOut(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No alerting: filtered notification: $str1"
        })
    }

    fun logNoAlertingGroupAlertBehavior(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No alerting: suppressed due to group alert behavior: $str1"
        })
    }

    fun logNoAlertingSuppressedBy(
        entry: NotificationEntry,
        suppressor: NotificationInterruptSuppressor,
        awake: Boolean
    ) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
            str2 = suppressor.name
            bool1 = awake
        }, {
            "No alerting: aborted by suppressor: $str2 awake=$bool1 sbnKey=$str1"
        })
    }

    fun logNoAlertingRecentFullscreen(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No alerting: recent fullscreen: $str1"
        })
    }

    fun logNoPulsingSettingDisabled(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No pulsing: disabled by setting: $str1"
        })
    }

    fun logNoPulsingBatteryDisabled(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No pulsing: disabled by battery saver: $str1"
        })
    }

    fun logNoPulsingNoAlert(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No pulsing: notification shouldn't alert: $str1"
        })
    }

    fun logNoPulsingNoAmbientEffect(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No pulsing: ambient effect suppressed: $str1"
        })
    }

    fun logNoPulsingNotImportant(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "No pulsing: not important enough: $str1"
        })
    }

    fun logPulsing(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
        }, {
            "Pulsing: $str1"
        })
    }

    fun logNoFullscreen(entry: NotificationEntry, reason: String) {
<<<<<<< HEAD
        hunBuffer.log(TAG, DEBUG, {
            str1 = entry.key
=======
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
>>>>>>> 00e5a18be27a12d55faacfe31d5e2f57c377a7f5
            str2 = reason
        }, {
            "No FullScreenIntent: $str2: $str1"
        })
    }

    fun logNoFullscreenWarning(entry: NotificationEntry, reason: String) {
<<<<<<< HEAD
        hunBuffer.log(TAG, WARNING, {
            str1 = entry.key
=======
        buffer.log(TAG, WARNING, {
            str1 = entry.logKey
>>>>>>> 00e5a18be27a12d55faacfe31d5e2f57c377a7f5
            str2 = reason
        }, {
            "No FullScreenIntent: WARNING: $str2: $str1"
        })
    }

    fun logFullscreen(entry: NotificationEntry, reason: String) {
<<<<<<< HEAD
        hunBuffer.log(TAG, DEBUG, {
            str1 = entry.key
=======
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
>>>>>>> 00e5a18be27a12d55faacfe31d5e2f57c377a7f5
            str2 = reason
        }, {
            "FullScreenIntent: $str2: $str1"
        })
    }

<<<<<<< HEAD
    fun keyguardHideNotification(key: String) {
        hunBuffer.log(TAG, DEBUG, {
            str1 = key
=======
    fun keyguardHideNotification(entry: NotificationEntry) {
        buffer.log(TAG, DEBUG, {
            str1 = entry.logKey
>>>>>>> 00e5a18be27a12d55faacfe31d5e2f57c377a7f5
        }, {
            "Keyguard Hide Notification: $str1"
        })
    }
}

private const val TAG = "InterruptionStateProvider"
