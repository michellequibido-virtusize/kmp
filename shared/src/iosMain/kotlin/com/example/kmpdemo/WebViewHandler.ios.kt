package com.example.kmpdemo

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.*
import platform.Foundation.*
import platform.WebKit.*

@OptIn(ExperimentalForeignApi::class)
actual fun openWebView(url: String) {
    val webViewController = UIViewController()
    val webView = WKWebView(frame = UIScreen.mainScreen.bounds)

    webView.loadRequest(
        NSURLRequest(NSURL(string = url))
    )

    webViewController.view = webView

    val rootVC =
        UIApplication.sharedApplication.keyWindow?.rootViewController

    rootVC?.presentViewController(
        webViewController,
        animated = true,
        completion = null
    )
}