package com.example.beerapp.ui.main

import com.example.beerapp.persistance.AppDatabase
import com.example.beerapp.persistance.Beer
import com.example.beerapp.persistance.BeerDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val beerDao: BeerDao) {
    suspend fun insertBeer(beer: Beer) {
        beerDao.insertBeer(beer)
    }

}