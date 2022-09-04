package com.mock.postfeed.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostFeedAPI {
    @GET("fayaz07/mock-post-feed-lambda/main/data/set_1.json")
    suspend fun getPosts(): Response<List<PostModel>>
}
