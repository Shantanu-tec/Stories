package com.example.stories.models

data class StoryModel(
    var id:String?=null,
    var name:String?=null,
    var username:String?=null,
    var thumbnail:String?=null,
    var photos: ArrayList<Photos>?=null
)

data class Photos(
    var id:String?=null,
    var title:String?=null,
    var url:String?=null,
    var thumbnailUrl:String?=null
)
