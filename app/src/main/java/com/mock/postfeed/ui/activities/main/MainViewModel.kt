package com.mock.postfeed.ui.activities.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mock.postfeed.data.network.PostFeedAPI
import com.mock.postfeed.data.network.PostModel
import com.mock.postfeed.data.network.RetrofitConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val TAG = "mainViewModel"

    private val _state = MutableStateFlow(MainActivityState())
    val state: StateFlow<MainActivityState>
        get() = _state


    fun getPosts() {
        val retrofit = RetrofitConfig.build()
        val api = retrofit.create(PostFeedAPI::class.java)
        viewModelScope.launch {
            val response = api.getPosts()
            if (response.isSuccessful) {
                _state.value = _state.value.copy(posts = response.body() as List<PostModel>)
            } else {
                response.errorBody()?.string()?.let { Log.e(TAG, it) }
            }
        }
    }

}