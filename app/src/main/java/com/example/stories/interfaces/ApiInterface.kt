package com.prasarbharati.bharat.ott.interfaces

import com.example.stories.utils.Apis
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {
    @GET(Apis.GET_PHOTOS)
    suspend fun GET_PHOTOS(): Response<String?>?

    @GET(Apis.GET_USER)
    suspend fun GET_USER(): Response<String?>?
}