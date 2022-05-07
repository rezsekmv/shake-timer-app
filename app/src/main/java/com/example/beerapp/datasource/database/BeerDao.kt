package com.example.beerapp.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.beerapp.model.entity.BeerEntity

@Dao
interface BeerDao {

    @Query("SELECT * FROM beer")
    suspend fun getAll(): List<BeerEntity>

    @Query("SELECT * FROM beer WHERE id=:id")
    suspend fun getBeer(id: Int): BeerEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeer(beer: BeerEntity)
}