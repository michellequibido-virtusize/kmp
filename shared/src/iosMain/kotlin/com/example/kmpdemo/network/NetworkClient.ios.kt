package com.example.kmpdemo.network

import io.ktor.client.engine.darwin.*

actual fun httpClientEngine() = Darwin.create()

