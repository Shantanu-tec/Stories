package com.example.stories

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.stories.story.activity.MainActivity
import com.example.stories.story.adapter.StoryAdapter
import androidx.test.espresso.action.ViewActions.click
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewDisplayedCorrectly() {
        Thread.sleep(6000)
        onView(withId(R.id.storyRecycler))
            .check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.storyRecycler)).perform(RecyclerViewActions.actionOnItemAtPosition<StoryAdapter.ViewHolder>(0, click()))
        Thread.sleep(1000)
        onView(withId(R.id.storyViewPager))
            .check(matches(isDisplayed()))
        Thread.sleep(10000)
        onView(withId(R.id.storyViewPager)).perform(ViewPager2Actions.scroll(ViewPager2Actions.RIGHT))
        for (i in 0..3){
            Thread.sleep(1500)
            onView(withId(R.id.rightTouchPanel)).perform(click())
        }
        Thread.sleep(500)
        onView(withId(R.id.storyViewPager)).perform(ViewPager2Actions.scroll(ViewPager2Actions.RIGHT))
    }
}