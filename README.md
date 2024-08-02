# Stories
A Simple Demo For InstaGram Stories

To use or clone  the project : git clone https://github.com/Shantanu-tec/Stories.git


# Architecture followed :-
MVVM With Dagger-Hilt with optimizable Background task

# AndroidJUnit

`kotlin`
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
