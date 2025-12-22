package com.example.kmpdemo

import com.example.kmpdemo.network.APIService
import com.example.kmpdemo.network.provideHttpClient

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}