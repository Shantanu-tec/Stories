package com.example.stories

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.example.stories.story.animator.BoxAnimationTransformer
import com.example.stories.utils.Const
import org.hamcrest.Matcher

object ViewPager2Actions {
    const val LEFT = "0"
    const val RIGHT = "0"

    fun scroll(action:String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(ViewPager2::class.java)
            }

            override fun getDescription(): String {
                return "Scroll right in ViewPager2"
            }

            override fun perform(uiController: UiController, view: View) {
                val viewPager2 = view as ViewPager2
                val currentItem = viewPager2.currentItem
                val nextItem = currentItem + 1
                val previousItem = currentItem - 1
                if (action == LEFT){
                    if (previousItem > 0) {
                        viewPager2.setCurrentItem(nextItem, true)
                    }
                }else{
                    if (nextItem < (viewPager2.adapter?.itemCount ?: 0)) {
                        viewPager2.setCurrentItem(nextItem, true)
                    }
                }

                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}
