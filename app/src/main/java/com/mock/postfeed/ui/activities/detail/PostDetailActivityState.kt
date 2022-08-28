package com.mock.postfeed.ui.activities.detail

import com.mock.postfeed.data.network.PostModel

data class PostDetailActivityState(
    val loading: Boolean = false,
    val postId: Int = 0,
    val postData: PostModel? = null
)
