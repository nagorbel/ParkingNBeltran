package com.example.parkingnbeltran.view.bookings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingnbeltran.databinding.ItemBookingBinding
import com.example.parkingnbeltran.domain.BookingItem

class BookingsAdapter(private val items: List<BookingItem>,
                      private val clickListener: BookingClickListener):
    RecyclerView.Adapter<BookingsAdapter.BookingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBookingBinding.inflate(layoutInflater, parent, false)
        return BookingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class BookingViewHolder(private val binding: ItemBookingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookingItem) {
            binding.bookingItem = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}