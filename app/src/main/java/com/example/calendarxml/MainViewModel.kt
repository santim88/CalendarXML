package com.example.calendarxml

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _dateText = MutableLiveData<String>("8 mes 2021 ")
    val dateText: LiveData<String> = _dateText

    fun setDateText(dateString: String) {
        _dateText.value = dateString
    }

    fun setDate(dateViewModel: String) {

    }
}