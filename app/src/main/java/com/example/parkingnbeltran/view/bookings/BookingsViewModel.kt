package com.example.parkingnbeltran.view.bookings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.parkingnbeltran.data.DataRepository
import com.example.parkingnbeltran.domain.BookingItem
import com.example.parkingnbeltran.domain.Type
import java.text.SimpleDateFormat

class BookingsViewModel : ViewModel() {

    private val dataRepository: DataRepository = DataRepository()

    val getBookings: LiveData<List<BookingItem>> =
        dataRepository.getBookingsFromFirestore().map { bookings ->
            bookings.map {
                    BookingItem(
                        date = SimpleDateFormat("yyyy.MM.dd").format(it.startingHour),
                        time = SimpleDateFormat("HH:mm").format(it.startingHour),
                        progress = 50,
                        image = 0,
                        type = if (it.startingHour > System.currentTimeMillis()) {
                            Type.NEXT
                        } else {
                            Type.ONGOING
                        }
                    )
                }.sortedBy { x -> x.date }
            }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text



}