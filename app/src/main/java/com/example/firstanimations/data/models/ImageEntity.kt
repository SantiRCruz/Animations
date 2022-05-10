package com.example.firstanimations.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val code:Int = 0,
    val file:String = ""
)
