package com.example.beerapp.di

import com.example.beerapp.persistance.Beer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun getBeers(): List<Beer> {
        return Collections.emptyList()
    }

    @Provides
    fun getBeer(): Beer {
        return Beer(0, "asd", "asd")
    }
}