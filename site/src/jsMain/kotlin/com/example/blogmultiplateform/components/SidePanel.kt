package com.example.blogmultiplateform.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.blogmultiplateform.models.Theme
import com.example.blogmultiplateform.navigation.Screen
import com.example.blogmultiplateform.utils.Constants.COLLAPSED_PANEL_HEIGHT
import com.example.blogmultiplateform.utils.Constants.SIDE_PANEL_WIDTH
import com.example.blogmultiplateform.utils.FONT_FAMILY
import com.example.blogmultiplateform.utils.Id
import com.example.blogmultiplateform.utils.Res
import com.example.blogmultiplateform.utils.logOut
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.leftRight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.onMouseOut
import com.varabyte.kobweb.compose.ui.modifiers.onMouseOver
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.topBottom
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.translateX
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaBars
import com.varabyte.kobweb.silk.components.icons.fa.FaXmark
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh


/**
 *todo: Created by `Mr Singh` on `24-11-2024` at `19:10`
 */

@Composable
fun SidePanel(onMenuClick: () -> Unit = { }) {
    val breakpoint = rememberBreakpoint()
    if (breakpoint > Breakpoint.MD) {
        SidePanelInternal()
    } else {
        CollapsedSidePanel(onMenuClick)
    }
}


@Composable
private fun SidePanelInternal() {

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

        NavigationItems()
    }
}

@Composable
private fun CollapsedSidePanel(onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(COLLAPSED_PANEL_HEIGHT.px)
            .padding { leftRight(24.px) }
            .backgroundColor(Theme.Secondary.rgb),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FaBars(
            modifier = Modifier
                .margin { right(24.px) }
                .color(Colors.White)
                .cursor(Cursor.Pointer)
                .onClick { onMenuClick() },
            size = IconSize.XL
        )
        LogoImage()
    }
}

@Composable
fun OverFlowSidePanel(onMenuClosed: () -> Unit, ) {
    val breakpoint = rememberBreakpoint()
    val scope = rememberCoroutineScope()

    var translateX by remember { mutableStateOf((-100).percent) }
    var opacity by remember { mutableStateOf(0.percent) }

    LaunchedEffect(key1 = breakpoint) {
        translateX = 0.percent
        opacity = 100.percent

        if (breakpoint > Breakpoint.MD) {
            scope.launch {
                translateX = (-100).percent
                opacity = 0.percent
                delay(500)
                onMenuClosed()
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.vh)
            .position(Position.Fixed)
            .zIndex(9)
            .opacity(opacity)
            .transition(
                Transition.of(
                    property = "opacity", duration = 500.ms,
                    timingFunction = null, delay = null
                )
            )
            .overflow(Overflow.Auto)
            .scrollBehavior(ScrollBehavior.Smooth)
            .backgroundColor(Theme.HalfBlack.rgb)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(if (breakpoint < Breakpoint.MD) 50.percent else 25.percent)
                .backgroundColor(Theme.Secondary.rgb)
                .translateX(translateX)
                .transition(
                    Transition.of(
                        property = "translate", duration = 500.ms,
                        timingFunction = null, delay = null
                    )
                )
                .padding {
                    leftRight(40.px)
                    topBottom(40.px)
                }
        ) {
            Row(
                modifier = Modifier.margin {
                    bottom(60.px)
                    top(24.px)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                FaXmark(
                    modifier = Modifier
                        .margin { right(20.px) }
                        .color(Colors.White)
                        .onClick {
                            scope.launch {
                                translateX = (-100).percent
                                opacity = 0.percent
                                delay(500)
                                onMenuClosed()
                            }
                        },
                    size = IconSize.LG
                )
                LogoImage()
            }
            NavigationItems()
        }
    }
}


@Composable
private fun NavigationItems(pageContext: PageContext = rememberPageContext()) {
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

@Composable
private fun NavigationItem(
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
private fun VectorIcon(
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

@Composable
fun LogoImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.width(80.px),
        src = Res.Image.logo,
        description = "Logo Image"
    )
}
