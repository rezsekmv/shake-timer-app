package com.example.beerapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerapp.persistance.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun insertBeer() {
        viewModelScope.launch {
            repository.insertBeer(
                Beer(14, "Kőbányai", "finom")
            )
        }
    }
}