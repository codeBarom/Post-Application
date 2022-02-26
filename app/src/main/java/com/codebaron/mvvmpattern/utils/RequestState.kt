package com.codebaron.mvvmpattern.utils

import retrofit2.HttpException

open class RequestState<out R> {

    data class Success<out T>(val data: T) : RequestState<T>()
    data class ErrorHttp(val exception: HttpException) : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
}