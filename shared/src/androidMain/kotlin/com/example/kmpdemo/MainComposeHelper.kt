package com.example.kmpdemo

import android.content.Context
import androidx.compose.ui.platform.ComposeView

fun createMainComposeView(context: Context): ComposeView = ComposeView(context).apply {
    setContent {
        UserScreen(UserViewModel())
    }
}