package com.example.parkingnbeltran.domain
import com.example.parkingnbeltran.domain.Space
import com.example.parkingnbeltran.domain.Vehicle

data class Booking(
    val bookingId: Int,
    val date: Long,
    val startingHour: Long,
    val endingHour: Long,
    val vehicleId: Vehicle,
    val spaceId: Space,
)

