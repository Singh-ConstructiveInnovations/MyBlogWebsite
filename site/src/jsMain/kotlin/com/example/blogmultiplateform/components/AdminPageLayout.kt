package com.example.blogmultiplateform.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.blogmultiplateform.utils.Constants.PAGE_WIDTH
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import org.jetbrains.compose.web.css.px

/**
 *todo: Created by Mr Singh on 27-11-2024 at 22:39
 */

@Composable
fun AdminPageLayout(content: @Composable () -> Unit) {
    var overFlowMenu by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .maxWidth(PAGE_WIDTH.px)
        ) {
            SidePanel {
                println("onPanel open clicked ")
                overFlowMenu = true
            }

            if (overFlowMenu) {
                OverFlowSidePanel {
                    println("onPanel close clicked")
                    overFlowMenu = false
                }
            }

            content()
        }

    }
}
