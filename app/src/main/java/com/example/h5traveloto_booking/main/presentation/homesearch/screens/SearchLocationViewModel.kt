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


fun getSearchPopular(): List<Suggestion>{
    val nhaTrang = Suggestion("landmarks_enriched","56","Khánh Hòa","Nha Trang, Khánh Hòa",258.07596,null,null,null)
    val hanoi = Suggestion("provinces","01","Hà Nội","",110.65946,null,null,null)
    val hoiAn = Suggestion("landmarks_enriched","49","Quảng Nam","Hội An, Quảng Nam",258.07596,null,null,null)
    val hcm = Suggestion("provinces" ,"79","Hồ Chí Minh","",110.65946,null,null,null)
    val daNang = Suggestion("provinces","48","Đà Nẵng","",258.07596,null,null,null)
    val phuQuoc = Suggestion("landmarks_enriched","47","Phú Quốc","Phú Quốc, Kiên Giang",258.07596,null,null,null)
    val vungTau = Suggestion("provinces","77","Bà Rịa - Vũng Tàu","",258.07596,null,null,null)
    return listOf(nhaTrang, hanoi, hoiAn, hcm, daNang, phuQuoc, vungTau)
}