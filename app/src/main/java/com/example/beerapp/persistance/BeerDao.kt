package com.example.beerapp.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BeerDao {

    @Query("SELECT * FROM beer")
    suspend fun getAll(): List<Beer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeer(beer: Beer)
}