package com.example.blogmultiplateform.styles

import com.example.blogmultiplateform.models.Theme
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.ms

/**
 *todo: Created by Mr Singh on 24-11-2024 at 21:08
 */


@InitSilk
fun enableHoverEffect(ctx: InitSilkContext) = ctx.stylesheet.apply {
    registerStyle("sidePanelNavigationHoverEffect") {
        base {
            Modifier
                .backgroundColor(Colors.White) // Default background color
                .transition(
                    Transition.of(
                        property = "background-color", duration = 300.ms,
                        timingFunction = null, delay = null
                    )
                )
        }
        cssRule(":hover > #svgParent > #vectorIcon") {
            Modifier.styleModifier {
                property("stroke", Theme.Primary.hex)
            }
        }
    }
}