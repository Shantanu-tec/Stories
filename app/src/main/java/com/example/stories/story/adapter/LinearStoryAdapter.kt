package com.example.stories.story.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stories.databinding.ItemLinearStoryBinding
import com.example.stories.models.StoryModel
import gr.makris.androidstories.data.StoryItem

class LinearStoryAdapter(
    private val context: Context,
    private val storyList: ArrayList<StoryModel>
) : RecyclerView.Adapter<LinearStoryAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemLinearStoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemLinearStoryBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )

    override fun getItemCount() = storyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            with(storyList[position]){
                stories.destroyDrawingCache()
                val storiesList = listOf(
                    StoryItem(url = "https://image.tmdb.org/t/p/w92/dRLSoufWtc16F5fliK4ECIVs56p.jpg"),
                    StoryItem(url = "https://picsum.photos/300/500"),
                    StoryItem(url = "https://picsum.photos/300/500"),
                    StoryItem(url = "https://picsum.photos/300/500"),
                    StoryItem(url = "https://picsum.photos/300/500")
                )

                stories.setStoriesList(storiesList)
            }
        }
    }
}