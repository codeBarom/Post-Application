package com.codebaron.mvvmpattern.retrofit

import com.codebaron.mvvmpattern.model.PostDataItem
import retrofit2.http.GET

interface PostInterface {

    @GET("posts")
    suspend fun getAllPost(): List<PostDataItem>
}