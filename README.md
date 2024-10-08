# Stories
A Simple Demo For InstaGram Stories

To use or clone  the project : git clone https://github.com/Shantanu-tec/Stories.git


# Architecture followed :-
MVVM With Dagger-Hilt with optimizable Background task

`kotlin`

      @HiltAndroidApp
      class BaseAppClass : Application() 

      @Module
      @InstallIn(SingletonComponent::class)
      class AppModule  //For retrofit Injection

      @HiltViewModel
      class DataViewModel @Inject constructor(
      private val repository: ApiRepository
      ) : ViewModel() // For ViewModel Injection


# AndroidJUnit

`kotlin`

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

     ******** Do this if Device Animation is on*********
    IdlingPolicies.setMasterPolicyTimeout(15,TimeUnit.SECONDS)
    IdlingPolicies.setIdlingResourceTimeout(15,TimeUnit.SECONDS)
    ****************************************************

    Better to go with Device AnimationScale to off


# Image Caching Strategy

`kotlin`

        Glide.with(context)
            .load(story.getResource())
            .diskCacheStrategy(DiskCacheStrategy.All)
            .skipMemoryCache(false)
            .centerCrop()
            .dontAnimate()
            .placeholder(imageContentView.drawable)
            .thumbnail(Glide
                .with(context)
                .load(oldStoryItem.getResource())
                .centerCrop())
            .listener(loadListener)
            .into(imageContentView)

