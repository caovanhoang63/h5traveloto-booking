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

class ItemSearchPopular(
    val title: String,
    val detail: String,
    val type: String,
)

fun CreateSearchPopular(): List<ItemSearchPopular>{
    val searchPopular2 = ItemSearchPopular("Hồ Chí Minh", "Việt Nam", "Thành phố")
    val searchPopular3 = ItemSearchPopular("Đà Nẵng", "Việt Nam", "Tỉnh")
    val searchPopular4 = ItemSearchPopular("Hội An", "Quảng Nam,Việt Nam", "Thành phố")
    val searchPopular5 = ItemSearchPopular("Nha Trang", "Khánh Hòa,Việt Nam", "Thành phố")
    val searchPopular6 = ItemSearchPopular("Phú Quốc", "Kiên Giang, Việt Nam", "Thành phố")
    val searchPopular7 = ItemSearchPopular("Hà Nội", "Việt Nam", "Thành phố")
    val searchPopular8 = ItemSearchPopular("Đà Lạt", "Lâm Đồng, Việt Nam", "Thành phố")
    val searchPopular9 = ItemSearchPopular("Huế", "Thừa Thiên Huế, Việt Nam", "Thành phố")
    val searchPopular10 = ItemSearchPopular("Sapa", "Lào Cai, Việt Nam", "Thành phố")
    val items: List<ItemSearchPopular> = listOf( searchPopular2, searchPopular3, searchPopular4, searchPopular5, searchPopular6, searchPopular7, searchPopular8, searchPopular9, searchPopular10)
    return items
}