package com.mock.postfeed.ui.activities.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PostDetailViewModel : ViewModel() {
    private val _state = MutableStateFlow(PostDetailActivityState())
    val state: StateFlow<PostDetailActivityState>
        get() = _state
}
