package com.codebaron.mvvmpattern.utils

interface EntityMapper<Entity> {

    fun mapEntity(entity: Entity): Entity
}