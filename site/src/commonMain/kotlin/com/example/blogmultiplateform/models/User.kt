package com.example.blogmultiplateform.models


/**
 *todo: Created by `Mr Singh` on `23-11-2024` at `19:51`
 */

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class User {
    val id: String
    val userName: String
    val password: String
}

expect class UserWithoutPassword {
    val id: String
    val userName: String
}