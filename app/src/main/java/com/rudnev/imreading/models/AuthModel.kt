package com.rudnev.imreading.models

data class AuthModel(
    val status: Boolean,
    val access_token: String,
    val error: String,
)
