package com.example.parkingnbeltran.domain

data class Booking(
    val startingHour: Long,
    val endingHour: Long,
    val vehicle: Vehicle,
    val space: Space,
)

