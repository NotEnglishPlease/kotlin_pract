package com.example.kotlin_pract.data.api.models

import com.squareup.moshi.Json

data class SysInfo(
    @Json(name = "type") val type: Int,
    @Json(name = "id") val id: Int,
    @Json(name = "country") val country: String,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
)