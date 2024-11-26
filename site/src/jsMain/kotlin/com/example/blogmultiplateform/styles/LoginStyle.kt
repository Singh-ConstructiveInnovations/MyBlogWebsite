package com.example.blogmultiplateform.styles

import com.example.blogmultiplateform.models.Theme
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.onFocus
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.selectors.focus
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

/**
 *todo: Created by `Mr Singh` on `22-11-2024` at `20:45`
 */


object LoginStyle {
    val Modifier.baseStyle: Modifier
        get() = border(
            width = 2.px,
            style = LineStyle.Solid,
            color = Colors.Transparent
        )
            .transition(CSSTransition(property = "border", duration = 300.ms))
            .onFocus {
                border(
                    width = 2.px,
                    style = LineStyle.Solid,
                    color = Theme.Primary.rgb
                )
            }
}


@InitSilk
fun loginStyle(ctx: InitSilkContext) = ctx.stylesheet.apply {
    registerStyle("loginStyle") {
        base {
            Modifier
                .border(
                    width = 2.px,
                    style = LineStyle.Solid,
                    color = Colors.Transparent
                )
                .transition(
                    Transition.of(
                        property = "border", duration = 300.ms,
                        timingFunction = null, delay = null
                    )
                )
        }

        focus {
            Modifier.border(
                width = 2.px,
                style = LineStyle.Solid,
                color = Theme.Primary.rgb
            )
        }
    }
}
