package com.codebaron.mvvmpattern.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.codebaron.mvvmpattern.R
import com.codebaron.mvvmpattern.adapter.PostAdapter
import com.codebaron.mvvmpattern.databinding.ActivityMainBinding
import com.codebaron.mvvmpattern.model.PostDataItem
import com.codebaron.mvvmpattern.utils.RequestState
import com.codebaron.mvvmpattern.utils.errorToast
import com.codebaron.mvvmpattern.utils.isNetworkAvailable
import com.codebaron.mvvmpattern.viewModel.EventHandler
import com.codebaron.mvvmpattern.viewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi @AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivity"
        lateinit var dialog: SpotsDialog
        var listOfPost = mutableListOf<PostDataItem>()
    }

    private lateinit var adapter: PostAdapter
    private lateinit var binding: ActivityMainBinding
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog = SpotsDialog(this, "Loading posts", R.style.Custom)

        initRecyclerView()

        if (isNetworkAvailable(this)){
            getPosts()
        } else
            errorToast(this, "Poor internet connection", Toast.LENGTH_LONG)
        dialog.dismiss()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getPosts() {
        postViewModel.setState(EventHandler.PostHandler)
        postViewModel.dataState.observe(this){
            when(it){
                is RequestState.Success<List<PostDataItem>> -> {
                    Log.d(TAG, "getPosts() returned: ${it.data}")
                    val posts: List<PostDataItem> = it.data
                    if (!posts.isNullOrEmpty()){
                        listOfPost.addAll(posts)
                        binding.postRecycler.adapter?.notifyDataSetChanged()
                        dialog.dismiss()
                    }
                }
                is RequestState.ErrorHttp -> {
                    Log.e(TAG, "getPosts: ${it.exception.response()?.message()}")
                    dialog.dismiss()
                }
                is RequestState.Loading -> {
                    Log.d(TAG, "getPosts() returned: LOADING...")
                    dialog.show()
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = PostAdapter(this, listOfPost)
        binding.postRecycler.adapter = adapter
        binding.postRecycler.layoutManager = LinearLayoutManager(this)
    }
}