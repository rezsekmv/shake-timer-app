package com.example.beerapp.repository

import android.util.Log
import com.example.beerapp.model.dto.BeerDTO
import com.example.beerapp.datasource.network.BeerService
import com.example.beerapp.model.entity.BeerEntity
import com.example.beerapp.datasource.database.BeerDao
import com.example.beerapp.model.dto.toBeerEntity
import com.example.beerapp.model.entity.toBeerDTO
import java.lang.NullPointerException
import javax.inject.Inject


class BeerRepository @Inject constructor(
    private val beerService: BeerService,
    private val beerDao: BeerDao
) {

    suspend fun getBeers(): List<BeerDTO> {
        return try {
            val beers = beerService.getBeers()
            beers.map {
                beerDao.insertBeer(it.toBeerEntity())
            }
            beers
        } catch (e: Exception) {
            Log.d("DEBUG", e.message.toString())
            beerDao.getAll().map {
                it.toBeerDTO()
            }
        }
    }

    suspend fun getBeer(id: Int): BeerDTO {
        return try {
            beerService.getBeerById(id)[0]
        } catch (e: Exception) {
            Log.d("DEBUG", e.message.toString())
            return try {
                beerDao.getBeer(id).toBeerDTO()
            } catch (ex: NullPointerException) {
                BeerDTO(0, "Sajnos nem sikerült betölteni az adatokat", "", "", "")
            }
        }
    }
}
