package com.codebaron.mvvmpattern.repo

import com.codebaron.mvvmpattern.mapper.PostMapper
import com.codebaron.mvvmpattern.retrofit.PostInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
object PostModule {

    @Singleton @Provides fun providePostRepository(
        postInterface: PostInterface,
        postMapper: PostMapper
    ): PostRepo{
        return PostRepo(postInterface, postMapper)
    }
}