package com.mock.postfeed.data.network

import retrofit2.Response
import retrofit2.http.GET

interface PostFeedAPI {
    @GET("/")
    suspend fun getPosts(): Response<List<PostModel>>
}
