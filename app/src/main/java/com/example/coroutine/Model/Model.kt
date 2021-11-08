package com.example.coroutine.Model

import com.google.gson.annotations.SerializedName


data class Model(
    @SerializedName("id")
    val postID: Int,
    @SerializedName("title")
    val postTitle: String,
    @SerializedName("body")
    val postBody: String
)