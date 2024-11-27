package com.example.blogmultiplateform.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.blogmultiplateform.models.Theme
import com.example.blogmultiplateform.navigation.Screen
import com.example.blogmultiplateform.utils.Constants.SIDE_PANEL_WIDTH
import com.example.blogmultiplateform.utils.FONT_FAMILY
import com.example.blogmultiplateform.utils.Id
import com.example.blogmultiplateform.utils.Res
import com.example.blogmultiplateform.utils.logOut
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.leftRight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.onFocusIn
import com.varabyte.kobweb.compose.ui.modifiers.onFocusOut
import com.varabyte.kobweb.compose.ui.modifiers.onMouseOut
import com.varabyte.kobweb.compose.ui.modifiers.onMouseOver
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.topBottom
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssRule
import com.varabyte.kobweb.silk.style.cssRules
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh


/**
 *todo: Created by `Mr Singh` on `24-11-2024` at `19:10`
 */

@Composable
fun SidePanel() {
//    var selectedNavigationItem by remember { mutableStateOf(0) }
    val pageContext = rememberPageContext()

    Column(
        modifier = Modifier
            .padding {
                leftRight(40.px)
                topBottom(50.px)
            }
            .width(SIDE_PANEL_WIDTH.px)
            .height(100.vh)
            .position(Position.Fixed)
            .backgroundColor(Theme.Secondary.rgb)
            .zIndex(9)
    ) {

        Image(
            modifier = Modifier.margin { bottom(60.px) },
            src = Res.Image.logo,
            description = "Logo Image"
        )

        SpanText(
            modifier = Modifier
                .margin { bottom(30.px) }
                .fontFamily(FONT_FAMILY.ROBOTO)
                .fontSize(14.px)
                .color(Theme.HalfWhite.rgb),
            text = "Dashboard"
        )

        val navigationItems = listOf(
            "Home" to Res.PathIcon.home,
            "Create a Post" to Res.PathIcon.create,
            "My Posts" to Res.PathIcon.posts,
            "Log Out" to Res.PathIcon.logout
        )

        repeat(navigationItems.size) { index ->
            val item = navigationItems[index]
            NavigationItem(
                modifier = Modifier.margin { bottom(24.px) },
                isSelected = when (index) {
                    0 -> pageContext.route.path.contains(Screen.Home.route)
                    1 -> pageContext.route.path.contains(Screen.CreateAPost.route)
                    2 -> pageContext.route.path.contains(Screen.MyPost.route)
                    else -> false
                },
                title = item.first,
                icon = item.second,
                onClick = {
                    when (index) {
                        0 -> pageContext.router.navigateTo(Screen.Home.route)
                        1 -> pageContext.router.navigateTo(Screen.CreateAPost.route)
                        2 -> pageContext.router.navigateTo(Screen.MyPost.route)
                        3 -> {
                            //todo: Pass the logic for logOut
                            logOut()
                            pageContext.router.navigateTo(Screen.Login.route)
                        }
                    }
                    println("${item.first} is Clicked")
                }
            )
        }

    }
}

@Composable
fun NavigationItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    val vectorIconColor =
        remember { mutableStateOf(Theme.HalfWhite.hex) }
    val spanTextColor =
        remember { mutableStateOf<CSSColorValue>(Colors.White) }

    LaunchedEffect(isSelected) {
        vectorIconColor.value =
            if (isSelected) Theme.Primary.hex else Theme.HalfWhite.hex
        spanTextColor.value =
            if (isSelected) Theme.Primary.rgb else Colors.White
    }

    Row(
        modifier = modifier.classNames("sidePanelNavigationHoverEffect")
            .cursor(Cursor.Pointer)
            .onMouseOver {
                if (!isSelected) {
                    vectorIconColor.value = Theme.Primary.hex
                    spanTextColor.value = Theme.Primary.rgb
                }
            }
            .onMouseOut {
                if (!isSelected) {
                    vectorIconColor.value = Theme.HalfWhite.hex
                    spanTextColor.value = Colors.White
                }
            }
            .transition(
                Transition.of(
                    property = TransitionProperty.All, duration = 300.ms,
                    timingFunction = null, delay = 10.ms
                )
            )
            .onClick { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        VectorIcon(
            modifier = Modifier.margin(right = 10.px),
            pathData = icon,
            color = vectorIconColor.value
        )

        SpanText(
            modifier = Modifier
                .fontFamily(FONT_FAMILY.ROBOTO)
                .fontSize(16.px)
                .color(spanTextColor.value),
            text = title,
        )

    }
}

@Composable
fun VectorIcon(
    modifier: Modifier = Modifier,
    pathData: String,
    color: String
) {
    Svg(
        attrs = modifier
            .id(Id.svgParent)
            .width(24.px)
            .height(24.px)
            .toAttrs {
                attr("viewBox", "0 0 24 24")
                attr("fill", "none")
            }
    ) {
        Path(
            attrs = Modifier
                .id(Id.vectorIcon)
                .toAttrs {
                    attr("d", pathData)
                    attr("stroke", color)
                    attr("stroke-width", "2")
                    attr("stroke-linecap", "round")
                    attr("stroke-linejoin", "round")
                }
        )
    }

}
