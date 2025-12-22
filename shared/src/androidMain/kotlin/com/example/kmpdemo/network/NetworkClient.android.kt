package com.example.kmpdemo.network

import io.ktor.client.engine.okhttp.*

actual fun httpClientEngine() = OkHttp.create()
