package com.example.stories.story.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stories.databinding.ItemStoryAdapterBinding
import com.example.stories.models.StoryModel
import com.example.stories.story.activity.MainActivity
import com.example.stories.utils.addFragment

class StoryAdapter(private val context: Context,private val storyModelList:ArrayList<StoryModel>) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemStoryAdapterBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemStoryAdapterBinding.inflate(LayoutInflater.from(context),parent,false)
    )

    override fun getItemCount() = storyModelList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            with(storyModelList[position]){
                Glide.with(context)
                    .load(thumbnail).into(imageView)

                profileName.text = username
            }
            mainRl.setOnClickListener {
                (context as MainActivity).apply {
                    openStory(position,storyModelList)
//                    addFragment(position,storyModelList)
//                    binding.container.isVisible = true
                }
            }
        }
    }
}