package com.example.beerapp.persistance;

import androidx.room.ColumnInfo
import androidx.room.Entity;
import androidx.room.PrimaryKey

@Entity
data class Beer (
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "details")
    val details: String
)
