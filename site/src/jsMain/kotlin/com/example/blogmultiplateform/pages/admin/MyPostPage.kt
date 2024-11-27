package com.example.blogmultiplateform.pages.admin

import androidx.compose.runtime.Composable
import com.example.blogmultiplateform.components.AdminPageLayout
import com.varabyte.kobweb.core.Page

/**
 *todo: Created by Mr Singh on 26-11-2024 at 22:17
 */


@Page(routeOverride = "posts")
@Composable
fun MyPostPage() {
//    isUserLoggedIn {

    PostScreen()

//    }

}

@Composable
fun PostScreen() {
    AdminPageLayout {

    }
}
