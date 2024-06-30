package com.example.parkingnbeltran.view.newBookng

import androidx.lifecycle.ViewModel
import com.example.parkingnbeltran.domain.Booking
import com.example.parkingnbeltran.domain.Space
import com.example.parkingnbeltran.domain.Vehicle
import com.example.parkingnbeltran.data.DataRepository


class NewBookingViewModel: ViewModel() {

    val dataRepository: DataRepository = DataRepository()
    fun addBookingToFirestore(startingHour:Long,endingHour:Long,vehicle:Vehicle,space: Space) {

        val newBooking = Booking(
            startingHour = startingHour,
            endingHour = endingHour,
            vehicle = vehicle,
            space = space
        )

        dataRepository.addBookingToFirestore(newBooking)
    }
}