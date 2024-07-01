package com.example.parkingnbeltran.view.newBookng

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingnbeltran.R
import com.example.parkingnbeltran.databinding.ActivityNewBookingBinding
import com.example.parkingnbeltran.domain.Space
import com.example.parkingnbeltran.domain.SpaceType
import com.example.parkingnbeltran.domain.Vehicle
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NewBookingActivity : AppCompatActivity(){


    private lateinit var binding: ActivityNewBookingBinding
    private val newBookingViewModel: NewBookingViewModel by viewModels()

    private var selectedDate: String = ""
    private var startTime: String? = null
    private var endTime: String? = null

    private var vehicle: Vehicle = Vehicle(0, " ")
    private var space: Space = Space (0, SpaceType.CAR )
    private var startDateTime: Long = 0
    private var endDateTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BtnPickDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnBook.setOnClickListener {
            if (validateInputs()) {
                saveReservation()
            } else {
                Toast.makeText(this, "Please complete all fields and ensure selections are valid.", Toast.LENGTH_SHORT).show()
            }
        }
        setupChipClickListeners()
    }

    fun showDatePickerDialog(){

        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val day = c[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                binding.TVSelectedDate.text =
                    (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                selectedDate = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun setupChipClickListeners() {
        val chipGroup = binding.CGHour
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            chip.setOnClickListener { onHoraSeleccionada(it) }
        }
    }

    fun onHoraSeleccionada(view: View) {
        val chip = view as Chip
        val selectedTime = chip.text.toString()

        if (startTime == null) {
            startTime = selectedTime
        } else if (endTime == null) {
            if (isValidEndTime(selectedTime)) {
                endTime = selectedTime
            } else {
                Toast.makeText(this, "Invalid end time selection. Ensure it is after the start time and within 9 hours.", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Reset if trying to select more than two times
            startTime = selectedTime
            endTime = null
        }
    }

    private fun isValidEndTime(selectedTime: String): Boolean {
        val sdf = SimpleDateFormat("HH:mm", Locale.US)
        val start = sdf.parse(startTime)
        val end = sdf.parse(selectedTime)
        val difference = end.time - start.time

        // Check if the difference is within 9 hours
        return end.after(start) && difference <= 9 * 60 * 60 * 1000
    }

    private fun calculateDateTimes() {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
        val startDateString = "$selectedDate $startTime"
        val endDateString = "$selectedDate $endTime"

        startDateTime = sdf.parse(startDateString)?.time ?: 0
        endDateTime = sdf.parse(endDateString)?.time ?: 0
    }

    private fun validateInputs(): Boolean {
        return selectedDate.isNotEmpty() && startTime != null && endTime != null
    }

    private fun getSelectedParkingType(): String {
        return when {
            binding.BtnCar.isChecked -> "Car"
            binding.BtnElectric.isChecked -> "Electric"
            binding.BtnMotor.isChecked -> "Motorcycle"
            binding.BtnDisabled.isChecked -> "Disabled"
            else -> "Unknown"
        }
    }

    private fun saveReservation() {
        calculateDateTimes()
        getSelectedParkingType()
        newBookingViewModel.addBookingToFirestore(startDateTime,endDateTime,vehicle,space)
    }

}