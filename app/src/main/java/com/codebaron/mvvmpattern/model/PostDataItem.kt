package com.codebaron.mvvmpattern.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostDataItem(

    @SerializedName("body")
    @Expose
    val body: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("userId")
    @Expose
    val userId: Int? = null
)