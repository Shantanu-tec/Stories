package com.example.stories.story.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stories.R
import com.example.stories.databinding.FragmentInstaStoryBinding
import com.example.stories.listener.StoriesCallback
import com.example.stories.models.StoryModel
import com.example.stories.story.adapter.LinearStoryAdapter
import com.example.stories.story.animator.BoxAnimationTransformer
import com.example.stories.story.custom.Stories
import gr.makris.androidstories.data.StoryItem


class InstaStoryFragment : Fragment() {
    private lateinit var binding: FragmentInstaStoryBinding
    private var position = -1
    private lateinit var storyList:ArrayList<StoryModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt("position")
            storyList = it.getSerializable("list")!! as ArrayList<StoryModel>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInstaStoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setData() = binding.apply {
        val storiesList = ArrayList<StoryItem>()
        storyList[position].photos!!.forEach {
            storiesList.add(StoryItem(url = it.url!!))
        }

        stories.apply {
            setStoriesList(storiesList)
        }
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        @JvmStatic fun newInstance(position: Int, list: ArrayList<StoryModel>) =
            InstaStoryFragment().apply {
                arguments = Bundle().apply {
                    putInt("position", position)
                    putSerializable("list", list)
                }
            }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }


}