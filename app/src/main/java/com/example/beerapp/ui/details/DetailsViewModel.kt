package com.example.beerapp.ui.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerapp.model.dto.BeerDTO
import com.example.beerapp.model.entity.toBeerDTO
import com.example.beerapp.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: BeerRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val beerLiveData = MutableLiveData<BeerDTO>(null)
    val id = savedStateHandle.get<Int>("id")

    init {
        id?.let { getBeer(it) }
    }

    fun getBeer(id: Int) {
        viewModelScope.launch {
            val beer = repository.getBeer(id)
            beerLiveData.value = beer
        }
    }

}