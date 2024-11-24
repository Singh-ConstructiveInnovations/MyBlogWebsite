package com.example.blogmultiplateform.api

import com.example.blogmultiplateform.data.MongoDB
import com.example.blogmultiplateform.models.User
import com.example.blogmultiplateform.models.UserWithoutPassword
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets
import java.security.MessageDigest


/**
 *todo: Created by `Mr Singh` on `23-11-2024` at `18:55`
 */

class UserNotFoundException(message: String) : Exception(message)

@Api(routeOverride = "userCheck")
suspend fun userCheck(context: ApiContext) {
    try {
        val userRequest = context.req.body?.decodeToString()?.let {  //todo: `req` lie for Request
            Json.decodeFromString<User>(it)
        }
        val user = userRequest?.let {
            context.data.getValue<MongoDB>().checkUserExistence(
                User(userName = it.userName, password = hashPassword(it.password))
            )
        }
        if (user != null) {
            context.res.setBodyText( //todo: `res` lie for Result
                Json.encodeToString(
                    UserWithoutPassword(id = user.id, userName = user.userName)
                )
            )
        } else {
            context.res.setBodyText(Json.encodeToString(UserNotFoundException("User's doesn't exist")))
        }
    } catch (e: Exception) {
        context.res.setBodyText(Json.encodeToString(UserNotFoundException(e.message.toString())))
    }
}

@Api(routeOverride = "checkUserId")
suspend fun checkUserId(context: ApiContext) {
    try {
        val idRequest =
            context.req.body?.decodeToString()?.let { Json.decodeFromString<String>(it) }
        val result = idRequest?.let {
            context.data.getValue<MongoDB>().checkUserId(it)
        }

        if (result != null) {
            context.res.setBodyText(Json.encodeToString(result))
        } else {
            context.res.setBodyText(Json.encodeToString(false))
        }
    } catch (e: Exception) {
        context.res.setBodyText(Json.encodeToString(false))
    }
}

private fun hashPassword(password: String): String {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val hashBytes = messageDigest.digest(password.toByteArray(StandardCharsets.UTF_8))
    val hexString = StringBuilder()

    hashBytes.forEach { byte ->
        hexString.append(String.format("%02x", byte))
    }
    return hexString.toString()
}