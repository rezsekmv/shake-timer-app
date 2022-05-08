package com.example.beerapp.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey
import com.example.beerapp.model.dto.BeerDTO

@Entity(tableName = "beer")
data class BeerEntity(
    @PrimaryKey
    val id: Int,
    val year: String,
    val name: String,
    val description: String,
    val image: String
)

fun BeerEntity.toBeerDTO() = BeerDTO(
    id = id,
    name = name,
    year = year,
    description = description,
    image = image
)