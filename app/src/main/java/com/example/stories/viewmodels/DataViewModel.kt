package com.example.stories.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stories.utils.hitApi
import com.prasarbharati.bharat.ott.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {


    private val handler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    private val photoMutable: MutableLiveData<JSONArray> = MutableLiveData()

    val photoLiveData: LiveData<JSONArray>
        get() = photoMutable

    fun getPhotos() = viewModelScope.launch(handler) {
        repository.getPhotos().hitApi {
            photoMutable.postValue(it)
        }
    }

    private val userMutable: MutableLiveData<JSONArray> = MutableLiveData()

    val userLiveData: LiveData<JSONArray>
        get() = userMutable


    fun getUserData() = viewModelScope.launch(handler) {
        repository.getUser().hitApi {
            userMutable.postValue(it)
        }
    }

}