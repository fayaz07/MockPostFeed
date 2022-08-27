package com.mock.postfeed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mock.postfeed.data.network.PostFeedAPI
import com.mock.postfeed.data.network.RetrofitConfig
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val TAG = "mainViewModel"

    init {
        getPosts()
    }

    fun getPosts() {
        val retrofit = RetrofitConfig.build()
        val api = retrofit.create(PostFeedAPI::class.java)
        viewModelScope.launch {
            val response = api.getPosts()
            if (response.isSuccessful){
                Log.d(TAG, response.body().toString())
            } else {
                response.errorBody()?.string()?.let { Log.e(TAG, it) }
            }
        }
    }

}