/*
 * Copyright 2018, The Android Open Source Project
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

package com.softvision.communitytrackerandroid

import android.app.Activity
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.softvision.communitytrackerandroid.data.DataObject
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * The kotlin equivalent to the ChangeTextBehaviorTest, that
 * showcases simple view matchers and actions like [ViewMatchers.withId],
 * [ViewActions.click] and [ViewActions.typeText], and ActivityScenarioRule
 *
 *
 * Note that there is no need to tell Espresso that a view is in a different [Activity].
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class CreateCommunityUITest {

    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test before each test,
     * and close it after each test. This is a replacement for
     * [androidx.test.rule.ActivityTestRule].
     */
    @get:Rule var activityScenarioRule = ActivityScenarioRule(ManageCommunityActivity::class.java)

    @Test
    fun createCommunity_inputValidCommunity() {

        // Type text and then press the button.
        onView(withId(R.id.editTextNameOfCommunity))
                .perform(typeText(STRING_TO_BE_TYPED_IN_NAME), closeSoftKeyboard())
        onView(withId(R.id.editDescriptionOfCommunity))
            .perform(typeText(STRING_TO_BE_TYPED_IN_DESCRIPTION), closeSoftKeyboard())
        val communityManager = DataObject.getAllData()

        // For spinner
        onView(withId(R.id.spinner)).perform(click())
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString(communityManager[1]))));

        onView(withId(R.id.btsave)).perform(click())

        onView(withId(R.id.alertTitle))
            .inRoot(isDialog())
            .check(matches(withText(R.string.community_Title)))
            .check(matches(isDisplayed()));

        onView(withId(android.R.id.message))
            .inRoot(isDialog())
            .check(matches(withText("Community Name: $STRING_TO_BE_TYPED_IN_NAME\nCommunity Assigned To: $STRING_TO_BE_SELECTED\nCommunity Description: $STRING_TO_BE_TYPED_IN_DESCRIPTION\n")))
            .check(matches(isDisplayed()));
    }

    companion object {
        val STRING_TO_BE_TYPED_IN_NAME = "Coffee"
        val STRING_TO_BE_TYPED_IN_DESCRIPTION = "Community for Mobile and cross-platform developers"
        val STRING_TO_BE_SELECTED = DataObject.getAllData()[1]
    }
}