package com.enkhmaa.a8145474assignment2.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val keypass: String
)
