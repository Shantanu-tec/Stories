package com.example.stories.story.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.stories.models.StoryModel
import com.example.stories.story.fragment.InstaStoryFragment

class HomeStateAdapter(
    private val context: Context,
    fragmentActivity: FragmentActivity,
    private val storyList: ArrayList<StoryModel>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = storyList.size

    override fun createFragment(position: Int) = InstaStoryFragment.newInstance(position,storyList)


}