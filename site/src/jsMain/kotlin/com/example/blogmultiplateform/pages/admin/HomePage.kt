package com.example.blogmultiplateform.pages.admin

import androidx.compose.runtime.Composable
import com.example.blogmultiplateform.utils.FONT_FAMILY
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px

/**
 *todo: Created by `Mr Singh` on `23-11-2024` at `21:45`
 */

@Page(routeOverride = "home")
@Composable
fun HomeScreen() {
//    isUserLoggedIn {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier.width(500.px)
                    .height(500.px)
                    .background(color = Colors.Red)
            ) {
                SpanText(
                    modifier = Modifier.width(350.px)
                        .color(color = Colors.Red)
                        .fontFamily(FONT_FAMILY.ROBOTO)
                        .textAlign(TextAlign.Center),
                    text = "Home Page"
                )
            }

        }
//    }
}

