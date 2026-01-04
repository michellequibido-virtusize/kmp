package com.example.kmpdemo.network

import com.example.kmpdemo.model.StackExchangeResponse
import com.example.kmpdemo.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class APIService(private val client: HttpClient) {
    suspend fun getUsers(): StackExchangeResponse {
        return client.get("https://api.stackexchange.com/2.3/users?site=stackoverflow&page=1&pagesize=20").body()
    }
}