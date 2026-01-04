package com.example.kmpdemo.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val user_id: Int,
    val display_name: String,
    val profile_image: String,
    val link: String
)