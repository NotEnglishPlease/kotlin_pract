package com.example.kotlin_pract.data

import java.util.UUID

data class City(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    var isFavorite: Boolean = false,
)
