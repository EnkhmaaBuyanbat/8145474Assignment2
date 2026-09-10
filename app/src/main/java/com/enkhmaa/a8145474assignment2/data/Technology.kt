package com.enkhmaa.a8145474assignment2.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Technology(
    val deviceName: String,
    val manufacturer: String,
    val operatingSystem: String,
    val releaseYear: Int,
    val description: String
) : Parcelable
