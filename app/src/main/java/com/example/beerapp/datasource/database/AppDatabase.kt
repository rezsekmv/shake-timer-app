package com.example.beerapp.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beerapp.model.entity.BeerEntity

@Database(entities = [BeerEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao
}