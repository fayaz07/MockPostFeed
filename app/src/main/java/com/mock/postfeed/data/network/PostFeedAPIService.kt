package com.mock.postfeed.data.network

object PostFeedAPIService {
    fun build(): PostFeedAPI {
        val retrofit = RetrofitConfig.build()
        return retrofit.create(PostFeedAPI::class.java)
    }
}
