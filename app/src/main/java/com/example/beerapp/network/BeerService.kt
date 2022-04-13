package com.example.beerapp.network

import com.example.beerapp.model.Beer
import retrofit2.Call
import retrofit2.http.GET

interface BeerService {

    @GET("beers")
    fun getBeers(): Call<List<Beer>>

    @GET("beer/{beerId}")
    fun getBeerById(): Call<Beer>
}