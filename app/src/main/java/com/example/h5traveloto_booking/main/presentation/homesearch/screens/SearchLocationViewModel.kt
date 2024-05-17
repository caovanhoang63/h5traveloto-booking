package com.example.h5traveloto_booking.main.presentation.homesearch.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.auth.data.remote.api.response
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.DistrictsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.Suggestion
import com.example.h5traveloto_booking.main.presentation.domain.repository.SearchRepository
import com.example.h5traveloto_booking.main.presentation.domain.usecases.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.text.Normalizer
import javax.inject.Inject

@HiltViewModel
class SearchLocationViewModel @Inject constructor(
    private val useCases: SearchUseCases
) : ViewModel() {
    var searchQuery = MutableStateFlow("")
    private val allCities = listOf("Hanoi", "Ho Chi Minh", "Da Nang", "Hoi An", "Nha Trang", "Phu Quoc", "Vung Tau", "Da Lat", "Hue", "Sapa")
    private var _suggestSearch: List<Suggestion> = listOf()


    fun String.unaccent(): String {
        val regex = "\\p{InCombiningDiacriticalMarks}+".toRegex()
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return regex.replace(temp, "")
            .replace("đ", "d")
            .replace("Đ", "D")

    }

    var filteredCities = searchQuery
        .debounce(300)
        .map { query ->
            if(query.isEmpty()) {
                listOf()
            } else {
                fetchDistrictsVN()
                _suggestSearch.filter { it.name.unaccent().contains(query.unaccent(), ignoreCase = true) }
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())


    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }



    suspend fun fetchDistrictsVN(){
        useCases.searchSuggestionUseCase(15,searchQuery.value).onStart {

        }
        .catch { e ->
            e.printStackTrace()
            Log.d("SearchLocationViewModel", "Error: ${e.message}")
        }
        .collect{ response ->
            _suggestSearch = response.suggestions.hits
            Log.d("SearchLocationViewModel", "Fetch Suggestion VN: $_suggestSearch")
        }
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