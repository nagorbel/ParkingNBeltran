package com.example.parkingnbeltran.view.bookings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkingnbeltran.data.DataRepository
import com.example.parkingnbeltran.domain.Booking
import com.example.parkingnbeltran.domain.Space
import com.example.parkingnbeltran.domain.SpaceType
import com.example.parkingnbeltran.domain.Vehicle
import kotlin.time.Duration.Companion.days

class BookingsViewModel : ViewModel() {

    private val dataRepository: DataRepository = DataRepository()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun addBookingToFirestore() {

        val newBooking = Booking(
            bookingId = 1,
            date = System.currentTimeMillis(),  // Replace with your date logic
            startingHour = System.currentTimeMillis(),  // Replace with your starting hour logic
            endingHour = System.currentTimeMillis(),  // Replace with your ending hour logic
            vehicleId = Vehicle(1,"hola"),  // Replace with your actual vehicle ID logic
            spaceId = Space(1, SpaceType.CAR)   // Replace with your actual space ID logic
        )

        dataRepository.addBookingToFirestore(newBooking)
    }
}