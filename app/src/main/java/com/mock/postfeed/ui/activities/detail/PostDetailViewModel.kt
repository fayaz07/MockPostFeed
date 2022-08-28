package com.mock.postfeed.ui.activities.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mock.postfeed.data.network.PostFeedAPIService
import com.mock.postfeed.data.network.PostModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel : ViewModel() {

    private val TAG = "postDetailViewModel"

    private val _state = MutableStateFlow(PostDetailActivityState())
    val state: StateFlow<PostDetailActivityState>
        get() = _state

    fun getPostById(id: Int) {
        setLoading(true)
        val api = PostFeedAPIService.build()
        viewModelScope.launch {
            val response = api.getPostById(id = id)
            if (response.isSuccessful) {
                _state.value =
                    _state.value.copy(postData = response.body() as PostModel, postId = id)
            } else {
                response.errorBody()?.string()?.let { Log.e(TAG, it) }
            }
            setLoading(false)
        }
    }

    private fun setLoading(status: Boolean) {
        _state.value = _state.value.copy(loading = status)
    }
}
