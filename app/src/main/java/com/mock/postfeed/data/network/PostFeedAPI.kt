package com.mock.postfeed.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostFeedAPI {
    @GET("/")
    suspend fun getPosts(@Query("API_KEY") apiKey: String = Endpoints.API_KEY): Response<List<PostModel>>
}
