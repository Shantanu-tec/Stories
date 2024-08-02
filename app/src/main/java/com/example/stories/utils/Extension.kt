package com.example.stories.utils

import android.app.Activity
import com.example.stories.R
import com.example.stories.models.Photos
import com.example.stories.models.StoryModel
import com.example.stories.story.activity.MainActivity
import com.example.stories.story.fragment.InstaStoryFragment
import com.google.gson.Gson
import org.json.JSONArray
import retrofit2.Response
import kotlin.math.min

fun Response<String?>?.hitApi(invokeOnCompletion: (JSONArray) -> Unit) {
    try {
        lateinit var jsonObject: JSONArray
        try {
            jsonObject = if (this!!.isSuccessful) {
                JSONArray(this.body().toString())
            } else {
                JSONArray(this.errorBody()?.string()!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            invokeOnCompletion.invoke(jsonObject)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Activity.addFragment(position: Int? = null, list: ArrayList<StoryModel>? = null) =
    (this as MainActivity).supportFragmentManager.beginTransaction().add(
        R.id.container,
        InstaStoryFragment.newInstance(position!!,list!!)
    ).commit()

fun MainActivity.getUserData(jsonArray: JSONArray) {
    storyList.clear()
    for (i in 0..<jsonArray.length()) {
        val story = Gson().fromJson(jsonArray.opt(i).toString(), StoryModel::class.java)
        storyList.add(story)
    }
    storyList.forEach {
        it.thumbnail = photoList[0].thumbnailUrl
        it.photos = ArrayList()
        it.photos!!.addAll(photoList)
    }

    setView()
}

fun MainActivity.getPhotosData(jsonArray: JSONArray){
    photoList.clear()
    for (i in 0..<min(4, jsonArray.length())) {
        val photos = Gson().fromJson(jsonArray.opt(i).toString(), Photos::class.java)
        photoList.add(photos)
    }
    if (SharedPreference.instance!!.getString(Const.USER_DATA).isEmpty()){
        dataViewModel.getUserData()
    }else{
        val jsonArrayUser = JSONArray(SharedPreference.instance!!.getString(Const.USER_DATA))
        getUserData(jsonArrayUser)
    }
}