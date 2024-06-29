package com.example.parkingnbeltran.view.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkingnbeltran.R
import com.example.parkingnbeltran.databinding.FragmentBookingsBinding
import com.example.parkingnbeltran.domain.BookingItem
import com.example.parkingnbeltran.domain.Type

class BookingsFragment : Fragment() {

    private lateinit var binding: FragmentBookingsBinding
    private val viewModel: BookingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentBookingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = BookingsAdapter(
            listOf(
                BookingItem(1,"20/07/2024","12:00",35, R.drawable.aliceblue, Type.NEXT),
                BookingItem(2,"21/07/2024","12:00",35, R.drawable.aliceblue, Type.ONGOING)
            ),
            object : BookingClickListener {
                override fun onMoreTimeClick(item: BookingItem) {
                    Toast.makeText(context, "More time ${item.id}", Toast.LENGTH_SHORT).show()
                }
                override fun onEndClick(item: BookingItem) {
                    Toast.makeText(context, "End ${item.id}", Toast.LENGTH_SHORT).show()
                }

                override fun onDeleteClick(item: BookingItem) {
                    Toast.makeText(context, "Delete ${item.id}", Toast.LENGTH_SHORT).show()
                }

                override fun onUpdateClick(item: BookingItem) {
                    Toast.makeText(context, "Update ${item.id}", Toast.LENGTH_SHORT).show()
                }
            }
        )
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = adapter

        return root
    }



}