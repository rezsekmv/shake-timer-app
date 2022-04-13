package com.example.beerapp.persistance

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Beer::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao
}