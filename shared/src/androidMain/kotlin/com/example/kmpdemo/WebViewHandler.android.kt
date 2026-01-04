package com.example.kmpdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.viewinterop.AndroidView

lateinit var androidContext: ComponentActivity

actual fun openWebView(url: String) {
    val intent = Intent(androidContext, WebViewActivity::class.java)
    intent.putExtra("url", url)
    androidContext.startActivity(intent)
}

class WebViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra("url") ?: return

        setContent {
            AndroidView(factory = {
                WebView(it).apply {
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            })
        }
    }
}