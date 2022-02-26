package com.codebaron.mvvmpattern.repo

import com.codebaron.mvvmpattern.mapper.PostMapper
import com.codebaron.mvvmpattern.model.PostDataItem
import com.codebaron.mvvmpattern.retrofit.PostInterface
import com.codebaron.mvvmpattern.utils.RequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PostRepo constructor(
    private val postInterface: PostInterface,
    private val postMapper: PostMapper
) {
    suspend fun getPost(): Flow<RequestState<List<PostDataItem>>> = flow {
        emit(RequestState.Loading)
        /**
         * Am only using this try block for this tutorial,
         * try improving it on real life implementation
         */
        try {
            val postRequest = postInterface.getAllPost()
            for (post in postRequest) {
                val postResult = postMapper.mapEntity(post)
                emit(RequestState.Success(postMapper.mapPostEntity(listOf(postResult))))
            }
        } catch (ex: HttpException) {
            /**Using HttpException will only catch
         exception that occurs within retrofit2, example: if this network request times out the
         app will crash....so i advice optimising this code on real life implementation*/
            emit(RequestState.ErrorHttp(ex))
        }
    }
}