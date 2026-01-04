package com.example.kmpdemo.model

import kotlinx.serialization.Serializable

@Serializable
data class StackExchangeResponse(
    val items: List<User>,
)