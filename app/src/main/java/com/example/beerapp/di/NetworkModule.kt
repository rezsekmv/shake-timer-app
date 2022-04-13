package com.example.beerapp.di

import com.example.beerapp.network.BeerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/v2")
            .build()
    }

    @Provides
    fun getBeerService(retrofit: Retrofit): BeerService {
        return retrofit.create(BeerService::class.java)
    }
}