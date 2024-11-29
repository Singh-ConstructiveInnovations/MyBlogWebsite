package com.example.blogmultiplateform.pages.admin

import androidx.compose.runtime.Composable
import com.example.blogmultiplateform.components.AdminPageLayout
import com.example.blogmultiplateform.models.Joke
import com.example.blogmultiplateform.models.Theme
import com.example.blogmultiplateform.navigation.Screen
import com.example.blogmultiplateform.utils.Constants.COLLAPSED_PANEL_HEIGHT
import com.example.blogmultiplateform.utils.Constants.PAGE_WIDTH
import com.example.blogmultiplateform.utils.Constants.SIDE_PANEL_WIDTH
import com.example.blogmultiplateform.utils.FONT_FAMILY
import com.example.blogmultiplateform.utils.Res
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.PointerEvents
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.BoxScope
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.pointerEvents
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.topBottom
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaPlus
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

/**
 *todo: Created by `Mr Singh` on `23-11-2024` at `21:45`
 */

@Page(routeOverride = "home")
@Composable
fun HomePage() {
    //    isUserLoggedIn {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    AdminPageLayout {
        val breakpoint = rememberBreakpoint()
        val context = rememberPageContext()

        Box(
            modifier = Modifier
                .height(100.vh)
                .fillMaxWidth()
                .padding {
                    left(if (breakpoint > Breakpoint.MD) SIDE_PANEL_WIDTH.px else 0.px)
                    top(if (breakpoint > Breakpoint.MD) 0.px else COLLAPSED_PANEL_HEIGHT.px)
                }
                .maxWidth(PAGE_WIDTH.px)
                .position(Position.Fixed)
                .pointerEvents(PointerEvents.None)
        ) {
            AddButton()
            HomeContent(joke = Joke(id = 2, joke = "Some random joke...."))
        }
    }
}

@Composable
fun HomeContentOld(joke: Joke?) {
    val breakpoint = rememberBreakpoint()
    Box(
        modifier = Modifier.fillMaxSize()
            .padding { left(if (breakpoint > Breakpoint.MD) SIDE_PANEL_WIDTH.px else 0.px) },
        contentAlignment = Alignment.Center
    ) {
        joke?.let {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding { topBottom(50.px) },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (joke.id != -1) {
                    Image(
                        modifier = Modifier.size(150.px)
                            .margin { bottom(50.px) },
                        src = Res.Image.laugh,
                        description = "Laugh Image"
                    )
                }

                if (joke.joke.contains("Q:")) {
                    SpanText(
                        text = joke.joke.split(":")[1],
                        modifier = Modifier
                            .margin { bottom(14.px) }
                            .fillMaxWidth(60.percent)
                            .textAlign(TextAlign.Center)
                            .color(Theme.Secondary.rgb)
                            .fontSize(28.px)
                            .fontFamily(FONT_FAMILY.ROBOTO)
                            .fontWeight(FontWeight.Bold)
                    )

                    SpanText(
                        text = joke.joke.split(":").last(),
                        modifier = Modifier.fillMaxWidth(60.percent)
                            .textAlign(TextAlign.Center)
                            .color(Theme.Secondary.rgb)
                            .fontSize(20.px)
                            .fontFamily(FONT_FAMILY.ROBOTO)
                            .fontWeight(FontWeight.Normal)
                    )
                } else {
                    SpanText(
                        text = joke.joke.split(":")[1],
                        modifier = Modifier
                            .margin { bottom(14.px) }
                            .fillMaxWidth(60.percent)
                            .textAlign(TextAlign.Center)
                            .color(Theme.Secondary.rgb)
                            .fontSize(28.px)
                            .fontFamily(FONT_FAMILY.ROBOTO)
                            .fontWeight(FontWeight.Bold)
                    )
                }
            }
        } ?: println("Loading....")
    }
}

@Composable
fun BoxScope.HomeContent(joke: Joke?) {
    joke?.let {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (joke.id != -1) {
                Image(
                    modifier = Modifier.size(150.px)
                        .margin { bottom(50.px) },
                    src = Res.Image.laugh,
                    description = "Laugh Image"
                )
            }

            if (joke.joke.contains("Q:")) {
                SpanText(
                    text = joke.joke.split(":")[1],
                    modifier = Modifier
                        .margin { bottom(14.px) }
                        .fillMaxWidth(60.percent)
                        .textAlign(TextAlign.Center)
                        .color(Theme.Secondary.rgb)
                        .fontSize(28.px)
                        .fontFamily(FONT_FAMILY.ROBOTO)
                        .fontWeight(FontWeight.Bold)
                )

                SpanText(
                    text = joke.joke.split(":").last(),
                    modifier = Modifier.fillMaxWidth(60.percent)
                        .textAlign(TextAlign.Center)
                        .color(Theme.Secondary.rgb)
                        .fontSize(20.px)
                        .fontFamily(FONT_FAMILY.ROBOTO)
                        .fontWeight(FontWeight.Normal)
                )

            } else {
                SpanText(
                    text = joke.joke.split(":").last(),
                    modifier = Modifier
                        .margin { bottom(14.px) }
                        .textAlign(TextAlign.Center)
                        .color(Theme.Secondary.rgb)
                        .fontSize(28.px)
                        .fontFamily(FONT_FAMILY.ROBOTO)
                        .fontWeight(FontWeight.Bold)
                )
            }
        }
    } ?: println("Loading....")
}


@Composable
fun BoxScope.AddButton() {
    val breakpoint = rememberBreakpoint()
    val context = rememberPageContext()
    Box(
        modifier = Modifier
            .margin {
                right(if (breakpoint > Breakpoint.MD) 40.px else 20.px)
                bottom(if (breakpoint > Breakpoint.MD) 40.px else 20.px)
            }
            .align(Alignment.BottomEnd)
            .size(if (breakpoint > Breakpoint.MD) 80.px else 50.px)
            .backgroundColor(Theme.Primary.rgb)
            .borderRadius(r = 14.px)
            .cursor(Cursor.Pointer)
            .pointerEvents(PointerEvents.Auto)
            .onClick {
                context.router.navigateTo(Screen.CreateAPost.route)
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(24.px).color(Colors.White),
            src = Res.Image.create_plus_icon,
            description = "Logo Image"
        )
    }
}
