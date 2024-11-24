package com.example.blogmultiplateform.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
actual data class User(
    @SerialName("_id")
    actual val id: String = "",
    actual val userName: String = "",
    actual val password: String = ""
)


@Serializable
actual data class UserWithoutPassword(
    @SerialName("_id")
    actual val id: String = "",
    actual val userName: String = ""
)
