package com.example.parkingnbeltran.domain

data class BookingItem(
    val id: Int,
    val date: String,
    val time: String,
    val progress: Int,
    val image: Int,
    val type: Type
)

enum class Type {
    NEXT,
    ONGOING,
    PAST
}
