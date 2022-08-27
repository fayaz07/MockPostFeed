package com.mock.postfeed

import com.mock.postfeed.data.network.PostModel

data class MainActivityState(
    val posts: List<PostModel> = emptyList()
)

