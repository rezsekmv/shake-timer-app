package com.example.beerapp

import android.content.Context
import androidx.room.Room
import com.example.beerapp.datasource.database.AppDatabase
import com.example.beerapp.datasource.database.BeerDao
import com.example.beerapp.di.PersistenceModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PersistenceModule::class]
)
object PersistenceModuleMock {

    @Provides
    @Singleton
    fun getBeerDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun getBeerDao(appDatabase: AppDatabase): BeerDao {
        return appDatabase.beerDao()
    }

}