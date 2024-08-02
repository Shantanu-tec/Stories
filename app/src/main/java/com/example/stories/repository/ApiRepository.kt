package com.prasarbharati.bharat.ott.repository

import com.prasarbharati.bharat.ott.interfaces.ApiInterface


class ApiRepository(private val apiInterface: ApiInterface) {
    suspend fun getPhotos() = apiInterface.GET_PHOTOS()
    suspend fun getUser() = apiInterface.GET_USER()

}