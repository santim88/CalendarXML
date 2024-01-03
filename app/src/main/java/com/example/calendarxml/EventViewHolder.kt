package com.example.calendarxml

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarxml.databinding.ItemEventCardBinding

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemEventCardBinding.bind(view)

    fun render(eventModel: Events) {
        binding.eventName.text = eventModel.name
        binding.eventHour.text = eventModel.hour
    }
}