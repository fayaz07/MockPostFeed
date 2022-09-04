package com.mock.postfeed.ui.activities.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mock.postfeed.data.network.PostFeedAPI
import com.mock.postfeed.data.network.PostFeedAPIService
import com.mock.postfeed.data.network.PostModel
import com.mock.postfeed.data.network.RetrofitConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val TAG = "mainViewModel"

    private val _state: MutableStateFlow<List<PostModel>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<PostModel>>
        get() = _state

    init {
        getPosts()
    }

    fun getPosts() {
        val api = PostFeedAPIService.build()
        viewModelScope.launch {
            val response = api.getPosts()
            if (response.isSuccessful) {
                _state.update {
                    response.body() as List<PostModel>
                }
            } else {
                response.errorBody()?.string()?.let { Log.e(TAG, it) }
            }
        }
    }
}