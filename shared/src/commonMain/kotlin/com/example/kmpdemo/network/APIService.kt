package com.example.kmpdemo.network

import com.example.kmpdemo.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class APIService(private val client: HttpClient) {
    suspend fun getUsers(): List<User> {
        return client.get("https://jsonplaceholder.typicode.com/users").body()
    }

    suspend fun getUser(id: Int): User {
        return client.get("https://jsonplaceholder.typicode.com/users/$id").body()
    }

    suspend fun createUser(user: User): User {
        return client.post("https://jsonplaceholder.typicode.com/users") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }.body()
    }
}