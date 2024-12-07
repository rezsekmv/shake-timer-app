package com.example.beerapp.datasource.network

import com.example.beerapp.model.dto.BeerDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface BeerService {

    @GET("beers")
    suspend fun getBeers(): List<BeerDTO>

    @GET("beers/{beerId}")
    suspend fun getBeerById(@Path("beerId") beerId: Int): List<BeerDTO>
}