package com.example.blogmultiplateform.utils

import com.example.blogmultiplateform.models.User
import com.example.blogmultiplateform.models.UserWithoutPassword
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

/**
 *todo: Created by Mr Singh on 23-11-2024 at 20:06
 */

suspend fun checkUserExistence(user: User): UserWithoutPassword? {
    return try {
        val result = window.api.tryPost(
            apiPath = "userCheck",
            body = Json.encodeToString(user).encodeToByteArray()
        )
        result?.let { Json.decodeFromString(it.decodeToString()) }
    } catch (e: Exception) {
        println(e.message)
        null
    }
}

suspend fun checkUserId(id: String): Boolean {
    return try {
        val result = window.api.tryPost(
            apiPath = "checkUserId",
            body = Json.encodeToString(id).encodeToByteArray()
        )

        result?.decodeToString()?.let { Json.decodeFromString<Boolean>(it) } ?: false
    } catch (e: Exception) {
        println(e.message.toString())
        false
    }
}