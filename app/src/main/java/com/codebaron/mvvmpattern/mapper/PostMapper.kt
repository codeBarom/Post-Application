package com.codebaron.mvvmpattern.mapper

import com.codebaron.mvvmpattern.model.PostDataItem
import com.codebaron.mvvmpattern.utils.EntityMapper
import javax.inject.Inject

class PostMapper @Inject constructor(): EntityMapper<PostDataItem> {
    override fun mapEntity(entity: PostDataItem): PostDataItem {
        return PostDataItem(
            body = entity.body,
            id = entity.id,
            title = entity.title,
            userId = entity.userId
        )
    }

    fun mapPostEntity(entity: List<PostDataItem>): List<PostDataItem>{
        return entity
    }
}