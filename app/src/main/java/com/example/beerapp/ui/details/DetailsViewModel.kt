package com.example.beerapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerapp.model.dto.BeerDTO
import com.example.beerapp.repository.BeerRepository
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: BeerRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    val beerLiveData = MutableLiveData<BeerDTO>(null)
    val id = savedStateHandle.get<Int>("id")

    init {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, id.toString())
        }
        id?.let { getBeer(it) }
    }

    fun getBeer(id: Int) {
        viewModelScope.launch {
            val beer = repository.getBeer(id)
            beerLiveData.value = beer
        }
    }

}