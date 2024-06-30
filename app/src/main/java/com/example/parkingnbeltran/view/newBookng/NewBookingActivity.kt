package com.example.parkingnbeltran.view.newBookng

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingnbeltran.databinding.ActivityNewBookingBinding
import java.util.Calendar

class NewBookingActivity : AppCompatActivity(){


    private lateinit var binding: ActivityNewBookingBinding
    private val newBookingViewModel: NewBookingViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.BtnPickDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    binding.TVSelectedDate.text =
                        (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }
}