/*
 * Copyright (C) 2020 The Android Open Source Project
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
package com.android.test.taskembed

import android.app.Instrumentation
import android.graphics.Rect
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.android.server.wm.flicker.monitor.LayersTraceMonitor
import com.android.server.wm.flicker.monitor.withSFTracing
import com.android.server.wm.flicker.traces.layers.LayersTraceSubject.Companion.assertThat
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.util.concurrent.CountDownLatch
import org.junit.Assert.assertNotEquals

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ResizeTasksSyncTest {
    @get:Rule
    var scenarioRule: ActivityScenarioRule<TaskOrganizerMultiWindowTest> =
            ActivityScenarioRule<TaskOrganizerMultiWindowTest>(
                    TaskOrganizerMultiWindowTest::class.java)

    protected val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()

    @Before
    fun setup() {
        val tmpDir = instrumentation.targetContext.dataDir.toPath()
        LayersTraceMonitor(tmpDir).stop()
        val firstTaskBounds = Rect(0, 0, 1080, 1000)
        val secondTaskBounds = Rect(0, 1000, 1080, 2000)

        lateinit var surfaceReadyLatch: CountDownLatch
        scenarioRule.getScenario().onActivity {
            surfaceReadyLatch = it.openTaskView(firstTaskBounds, secondTaskBounds)
        }
        surfaceReadyLatch.await()
    }

    @After
    fun teardown() {
        scenarioRule.getScenario().close()
    }

    @Test
    fun testResizeTwoTaskSync() {
        val firstBounds = Rect(0, 0, 1080, 800)
        val secondBounds = Rect(0, 1000, 1080, 1800)

        val trace = withSFTracing(instrumentation, TRACE_FLAGS) {
            lateinit var resizeReadyLatch: CountDownLatch
            scenarioRule.getScenario().onActivity {
                resizeReadyLatch = it.resizeTaskView(firstBounds, secondBounds)
            }
            resizeReadyLatch.await()
        }

        // find the frame which match resized buffer size.
        var frame: Long = -1
        loop@ for (trace in trace.entries) {
            for (layer in trace.flattenedLayers) {
                if (layer.proto.activeBuffer != null &&
                        layer.proto.activeBuffer.width == firstBounds.width() &&
                        layer.proto.activeBuffer.height == firstBounds.height()) {
                    frame = layer.proto.currFrame
                    break@loop
                }
            }
        }
        assertNotEquals(-1, frame)
        // layer bounds should be related to parent surfaceview.
        secondBounds.offsetTo(0, 0)

        // verify buffer size should be changed to expected values.
        assertThat(trace).layer(FIRST_ACTIVITY, frame).also {
            it.hasLayerSize(firstBounds)
            it.hasBufferSize(firstBounds)
        }

        assertThat(trace).layer(SECOND_ACTIVITY, frame).also {
            it.hasLayerSize(secondBounds)
            it.hasBufferSize(secondBounds)
        }
    }

    companion object {
        private const val TRACE_FLAGS = 0x1 // TRACE_CRITICAL
        private const val FIRST_ACTIVITY = "Activity1"
        private const val SECOND_ACTIVITY = "Activity2"
    }
}