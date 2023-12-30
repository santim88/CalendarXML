package com.example.calendarxml

import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.annotations.Nullable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var calendarView: CalendarView
    private lateinit var calendar: Calendar

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView = findViewById(R.id.calendarView) // Assuming you have a CalendarView with this ID
        calendar = Calendar.getInstance()

        setDate(3, 1, 2023)
        getDate()

        calendarView.setOnDateChangeListener { view, year, month, day ->
            Toast.makeText(this, "$day/${month + 1}/$year", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDate(day: Int, month: Int, year: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1) // Month in Calendar is 0-based
        calendar.set(Calendar.DAY_OF_MONTH, day)

        val milli = calendar.timeInMillis
        calendarView.date = milli
    }

    private fun getDate() {
        val date = calendarView.date
        val simpleDateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        val selectedDate = simpleDateFormat.format(Date(date)) // Converting milliseconds to Date object
        Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show()
    }
}