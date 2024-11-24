package com.example.blogmultiplateform.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.blogmultiplateform.models.Theme
import com.example.blogmultiplateform.models.User
import com.example.blogmultiplateform.models.UserWithoutPassword
import com.example.blogmultiplateform.styles.LoginStyle.baseStyle
import com.example.blogmultiplateform.utils.FONT_FAMILY
import com.example.blogmultiplateform.utils.Id
import com.example.blogmultiplateform.utils.Res
import com.example.blogmultiplateform.utils.checkUserExistence
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.set

/**
 *todo: Created by Mr Singh on 22-11-2024 at 19:25
 */


@Page(routeOverride = "login")
@Composable
fun LoginScreen() {

    val scope = rememberCoroutineScope()
    val context = rememberPageContext()
    var errorText by remember { mutableStateOf(" ") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(leftRight = 50.px, top = 80.px, bottom = 24.px)
                .background(color = Theme.LightGray.rgb),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.margin { bottom(50.px) }
                    .width(100.px),
                src = Res.Image.logo,
                description = "Logo Image",
            )

            Input(
                type = InputType.Text,
                attrs = Modifier
                    .margin { bottom(12.px) }
                    .id(Id.userNameInput)
                    .width(350.px)
                    .height(54.px)
                    .padding(leftRight = 20.px)
                    .backgroundColor(Colors.White)
                    .fontFamily(FONT_FAMILY.ROBOTO)
                    .baseStyle
                    .toAttrs {
                        attr(attr = "placeholder", value = "User Name")
                    }
            )
            Input(
                type = InputType.Text,
                attrs = Modifier
                    .margin { bottom(12.px) }
                    .id(Id.passwordInput)
                    .width(350.px)
                    .height(54.px)
                    .padding(leftRight = 20.px)
                    .backgroundColor(Colors.White)
                    .fontFamily(FONT_FAMILY.ROBOTO)
                    .baseStyle
                    .toAttrs {
//                        classes(InputStyles.inputField)
                        attr(attr = "placeholder", value = "Password")
                    }
            )

            Button(
                attrs = Modifier.margin { bottom(24.px) }.size(width = 350.px, height = 54.px)
                    .backgroundColor(color = Theme.Primary.rgb)
                    .color(color = Colors.White)
                    .borderRadius(r = 4.px)
                    .fontFamily(FONT_FAMILY.ROBOTO)
                    .fontWeight(FontWeight.Medium)
                    .fontSize(16.px)
                    .border {
                        width(0.px)
                        style(LineStyle.None)
                        color(Colors.Transparent)
                    }
                    .onClick {
                        scope.launch {
                            val userName =
                                (document.getElementById(Id.userNameInput) as HTMLInputElement).value
                            val password =
                                (document.getElementById(Id.passwordInput) as HTMLInputElement).value

                            if (userName.isNotEmpty() && password.isNotEmpty()) {
                                val user = checkUserExistence(
                                    user = User(
                                        userName = userName,
                                        password = password
                                    )
                                )

                                user?.let {
                                    rememberLoggedIn(remember = true, user = user)
                                    context.router.navigateTo("/admin/home")
                                } ?: run {
                                    errorText = "The User doesn't exist"
                                    delay(3000)
                                    errorText = ""
                                }
                                if (user != null) {


                                } else {

                                }
                            } else {
                                errorText = "Input fields are empty"
                                delay(3000)
                                errorText = ""
                            }
                        }
                    }
                    .cursor(Cursor.Pointer)
                    .outline(width = 0.px, style = LineStyle.None, color = Colors.Transparent)
                    .toAttrs()
            ) {
                SpanText(text = "Sign In")
            }

            SpanText(
                modifier = Modifier.width(350.px)
                    .color(color = Colors.Red)
                    .fontFamily(FONT_FAMILY.ROBOTO)
                    .textAlign(TextAlign.Center),
                text = errorText
            )

        }
    }
}

private fun rememberLoggedIn(
    remember: Boolean,
    user: UserWithoutPassword? = null
) {
    localStorage["remember"] = remember.toString()
    if (user != null) {
        localStorage["userId"] = user.id
        localStorage["userName"] = user.userName
    }

}