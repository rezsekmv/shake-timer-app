package com.example.beerapp.di

import android.content.Context
import androidx.room.Room
import com.example.beerapp.datasource.database.AppDatabase
import com.example.beerapp.datasource.database.BeerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun getBeerDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "beerDB"
        ).build()
    }

    @Provides
    @Singleton
    fun getBeerDao(appDatabase: AppDatabase): BeerDao {
        return appDatabase.beerDao()
    }
}