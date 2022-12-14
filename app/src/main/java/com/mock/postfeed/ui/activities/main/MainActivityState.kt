package com.mock.postfeed.ui.activities.main

import com.mock.postfeed.data.network.PostModel

data class MainActivityState(
    val loading: Boolean = false,
    val posts: List<PostModel> = emptyList()
)

