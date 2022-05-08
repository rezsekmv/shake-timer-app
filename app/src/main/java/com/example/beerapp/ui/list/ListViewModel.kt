package com.example.beerapp.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerapp.model.dto.BeerDTO
import com.example.beerapp.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: BeerRepository
) : ViewModel() {

    val beerList = MutableLiveData<List<BeerDTO>>(emptyList())

    init {
        getBeers()
    }

    fun getBeers() {
        viewModelScope.launch {
            val beers = repository.getBeers()
            beerList.value = beers
        }
    }
}