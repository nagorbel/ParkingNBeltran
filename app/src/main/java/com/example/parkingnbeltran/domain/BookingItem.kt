package com.example.parkingnbeltran.domain

data class BookingItem(
    val id: Long,
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
