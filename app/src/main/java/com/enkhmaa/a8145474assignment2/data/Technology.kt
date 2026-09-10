package com.enkhmaa.a8145474assignment2.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Technology(
    val deviceName: String,
    val manufacturer: String,
    val operatingSystem: String,
    val releaseYear: Int,
    val description: String
)
