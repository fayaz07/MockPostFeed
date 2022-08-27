package com.mock.postfeed.data.network

data class PostModel(
    val id: Int,
    val createdAt: String,
    val image: String,
    val username: String,
    val avatar: String,
    val likes: Int,
    val caption: String
)
