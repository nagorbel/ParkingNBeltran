package com.example.parkingnbeltran.domain
import com.example.parkingnbeltran.domain.Space
import com.example.parkingnbeltran.domain.Vehicle

data class Booking(
    val startingHour: Long,
    val endingHour: Long,
    val vehicle: Vehicle,
    val space: Space,
)

