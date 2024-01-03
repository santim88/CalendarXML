package com.example.calendarxml

import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarxml.databinding.ActivityMainBinding
import org.jetbrains.annotations.Nullable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val eventList = listOf(
        Events("Event 1", "16:27"),
        Events("Event 2", "17:30"),
        Events("Event 3", "23:32"),
        Events("Event 1", "16:27"),
        Events("Event 2", "17:30"),
        Events("Event 3", "23:32"),
        Events("Event 1", "16:27"),
        Events("Event 2", "17:30"),
        Events("Event 3", "23:32"),
        Events("Event 1", "16:27"),
        Events("Event 2", "17:30"),
        Events("Event 3", "23:32"),
    )

    private lateinit var calendarView: CalendarView
    private lateinit var calendar: Calendar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventAdapter

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeRecyclerView()

        calendarView =
            findViewById(R.id.calendarView) // Assuming you have a CalendarView with this ID
        calendar = Calendar.getInstance()

        setDate(3, 1, 2023)
        getDate()

        calendarView.setOnDateChangeListener { view, year, month, day ->
            Toast.makeText(this, "$day/${month + 1}/$year", Toast.LENGTH_SHORT).show()
        }

        viewModel.dateText.observe(this, Observer { dateString ->
            binding.dayAndMonthCalendar.text = dateString
        })



        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Format the date as you like, here's an example:
            val formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, month + 1, year)

            // Update the ViewModel's LiveData with the new date
            viewModel.setDateText(formattedDate)

            // Optionally show a toast with the selected date
            Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show()
        }

    }


    private fun setDate(day: Int, month: Int, year: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1) // Month in Calendar is 0-based
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val milli = calendar.timeInMillis
        calendarView.date = milli
    }


    private fun getDate(): String {
        val date = calendarView.date
        val simpleDateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        val selectedDate =
            simpleDateFormat.format(Date(date)) // Converting milliseconds to Date object
        Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show()
        return selectedDate
    }

    private fun initializeRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.tvRecyclerEvents.layoutManager = LinearLayoutManager(this)
        binding.tvRecyclerEvents.addItemDecoration(decoration)
        binding.tvRecyclerEvents.adapter = EventAdapter(eventList)
    }
}