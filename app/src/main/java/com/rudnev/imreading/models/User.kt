package com.rudnev.imreading.models

data class User(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val role: String,
    val photo: String,
    val experience: Int
)
