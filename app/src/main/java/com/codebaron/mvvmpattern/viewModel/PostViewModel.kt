package com.codebaron.mvvmpattern.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codebaron.mvvmpattern.model.PostDataItem
import com.codebaron.mvvmpattern.repo.PostRepo
import com.codebaron.mvvmpattern.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi @HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepo: PostRepo
): ViewModel() {

    private val _dataState: MutableLiveData<RequestState<List<PostDataItem>>> = MutableLiveData()
    val dataState: MutableLiveData<RequestState<List<PostDataItem>>>
        get() = _dataState

    fun setState(eventHandler: EventHandler){
        viewModelScope.launch {
            when(eventHandler){
                is EventHandler.PostHandler -> {
                    postRepo.getPost().onEach { _dataState.value = it }.launchIn(viewModelScope)
                }
            }
        }
    }
}

sealed class EventHandler{
    object PostHandler: EventHandler()
}