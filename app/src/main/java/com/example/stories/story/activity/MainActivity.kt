package com.example.stories.story.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stories.R
import com.example.stories.databinding.ActivityMainBinding
import com.example.stories.listener.StoriesCallback
import com.example.stories.models.Photos
import com.example.stories.models.StoryModel
import com.example.stories.story.adapter.HomeStateAdapter
import com.example.stories.story.adapter.StoryAdapter
import com.example.stories.story.animator.BoxAnimationTransformer
import com.example.stories.story.fragment.InstaStoryFragment
import com.example.stories.utils.Const
import com.example.stories.utils.SharedPreference
import com.example.stories.utils.getPhotosData
import com.example.stories.utils.getUserData
import com.example.stories.viewmodels.DataViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONArray
import kotlin.math.min


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), StoriesCallback {
    private lateinit var binding: ActivityMainBinding
    val dataViewModel: DataViewModel by viewModels()
    val photoList: ArrayList<Photos> = ArrayList()
    val storyList: ArrayList<StoryModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setObserver()
    }

    private fun setObserver() = dataViewModel.apply {

        if (SharedPreference.instance!!.getString(Const.PHOTOS_DATA).isEmpty()){
            getPhotos()
        }else{
            val jsonArray = JSONArray(SharedPreference.instance!!.getString(Const.PHOTOS_DATA))
            getPhotosData(jsonArray)
        }


        photoLiveData.observe(this@MainActivity) { jsonArray ->
            if (jsonArray.length() > 0) {
                SharedPreference.instance!!.putString(Const.PHOTOS_DATA,jsonArray.toString())
                getPhotosData(jsonArray)
            }
        }

        userLiveData.observe(this@MainActivity) { jsonArray ->
            if (jsonArray.length() > 0) {
                SharedPreference.instance!!.putString(Const.USER_DATA, jsonArray.toString())
                getUserData(jsonArray)
            }
        }

    }

    fun setView() = binding.apply {
        storyRecycler.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = StoryAdapter(this@MainActivity, storyList)
        }
    }


    override fun onBackPressed() {
        if (binding.storyViewPager.isVisible) {
            binding.storyViewPager.isVisible = false
        } else {
            super.onBackPressed()
        }
    }

    private var position = -1

    fun openStory(position: Int, storyModelList: ArrayList<StoryModel>) = binding.apply {
        this@MainActivity.position = position
        container.isVisible = false
        storyViewPager.apply {
            isVisible = true
            adapter = HomeStateAdapter(this@MainActivity, this@MainActivity, storyModelList)
            currentItem = position
            setPageTransformer(BoxAnimationTransformer())
        }
    }

    override fun onStoriesEnd() {

    }

    override fun onStoryPageEnd() {
        binding.storyViewPager.apply {
            if (position<storyList.size){
                position+=1
                setPageTransformer(BoxAnimationTransformer())
                post {
                    currentItem = position
                }
            }else{
                isVisible = false
            }
        }
    }

}