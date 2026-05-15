package com.example.nammashaaleinventory.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets")
data class Asset(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val serialNumber: String,
    val category: String,
    val condition: String,
    val imageUri: String
)