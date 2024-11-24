package com.example.blogmultiplateform.data

import com.example.blogmultiplateform.models.User

/**
 *todo: Created by Mr Singh on 22-11-2024 at 22:21
 */

interface MongoRepository {
    suspend fun checkUserExistence(user: User): User?
    suspend fun checkUserId(id: String): Boolean

}
