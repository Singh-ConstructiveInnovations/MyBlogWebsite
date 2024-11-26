package com.example.blogmultiplateform.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.blogmultiplateform.models.Theme
import com.example.blogmultiplateform.utils.Constants.SIDE_PANEL_WIDTH
import com.example.blogmultiplateform.utils.FONT_FAMILY
import com.example.blogmultiplateform.utils.Res
import com.varabyte.kobweb.compose.css.Cursor
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
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.topBottom
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssRule
import com.varabyte.kobweb.silk.style.cssRules
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh


/**
 *todo: Created by `Mr Singh` on `24-11-2024` at `19:10`
 */

@Composable
fun SidePanel() {
    var selectedNavigationItem by remember { mutableStateOf(0) }

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
                isSelected = selectedNavigationItem == index,
                title = item.first,
                icon = item.second,
                onClick = {
                    selectedNavigationItem = index
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
    Row(
        modifier = modifier.classNames("sidePanelNavigationHoverEffect")
            .cursor(Cursor.Pointer)
            .onClick { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        VectorIcon(
            modifier = Modifier.margin(right = 10.px),
            pathData = icon,
            color = if (isSelected) Theme.Primary.hex else Theme.HalfWhite.hex
        )

        SpanText(
            modifier = Modifier
                .fontFamily(FONT_FAMILY.ROBOTO)
                .fontSize(16.px)
                .color(if (isSelected) Theme.Primary.rgb else Colors.White),
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
            .id("svgParent")
            .width(24.px)
            .height(24.px)
            .toAttrs {
                attr("viewBox", "0 0 24 24")
                attr("fill", "none")
            }
    ) {
        Path(
            attrs = Modifier
                .id("vectorIcon")
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
