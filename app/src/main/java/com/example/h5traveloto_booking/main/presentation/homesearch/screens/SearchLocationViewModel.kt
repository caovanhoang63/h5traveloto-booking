package com.example.h5traveloto_booking.main.presentation.homesearch.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchLocationViewModel @Inject constructor() : ViewModel() {
    var searchQuery = MutableStateFlow("")
    private val allCities = listOf("Hanoi", "Ho Chi Minh", "Da Nang", "Hoi An", "Nha Trang", "Phu Quoc", "Vung Tau", "Da Lat", "Hue", "Sapa")
    var filteredCities = searchQuery
        .debounce(300)
        .map { query ->
            if(query.isEmpty()) {
                listOf()
            } else {
                allCities.filter { it.contains(query, ignoreCase = true) }
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily , listOf())

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}