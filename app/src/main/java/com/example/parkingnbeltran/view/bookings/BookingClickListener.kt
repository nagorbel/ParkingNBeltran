package com.example.parkingnbeltran.view.bookings

import com.example.parkingnbeltran.domain.BookingItem

interface BookingClickListener {
    fun onMoreTimeClick(item: BookingItem)
    fun onEndClick(item: BookingItem)
    fun onDeleteClick(item: BookingItem)

    fun onUpdateClick(item: BookingItem)
}