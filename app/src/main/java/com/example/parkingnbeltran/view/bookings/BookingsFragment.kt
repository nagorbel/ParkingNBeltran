package com.example.parkingnbeltran.view.bookings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkingnbeltran.databinding.FragmentBookingsBinding
import com.example.parkingnbeltran.domain.BookingItem
import com.example.parkingnbeltran.view.newBookng.NewBookingActivity
import java.util.Collections

class BookingsFragment : Fragment() {

    private lateinit var binding: FragmentBookingsBinding
    private val bookingViewModel: BookingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentBookingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = BookingsAdapter(
           Collections.emptyList(),
            object : BookingClickListener {
                override fun onMoreTimeClick(item: BookingItem) {
                    Toast.makeText(context, "More time ${item.date}", Toast.LENGTH_SHORT).show()
                }
                override fun onEndClick(item: BookingItem) {
                    Toast.makeText(context, "End ${item.date}", Toast.LENGTH_SHORT).show()
                }

                override fun onDeleteClick(item: BookingItem) {
                    Toast.makeText(context, "Delete ${item.date}", Toast.LENGTH_SHORT).show()
                }

                override fun onUpdateClick(item: BookingItem) {
                    Toast.makeText(context, "Update ${item.date}", Toast.LENGTH_SHORT).show()
                }
            }
        )

        binding.FABAddBooking.setOnClickListener { _ ->
            val intent =
                Intent(
                    requireContext(),
                    NewBookingActivity::class.java
                )
            startActivity(intent)
        }

        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = adapter
        bookingViewModel.getBookings.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        return root
    }



}