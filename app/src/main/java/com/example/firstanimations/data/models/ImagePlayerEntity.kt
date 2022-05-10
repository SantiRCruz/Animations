package com.example.firstanimations.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImagePlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val idImagePlayer:Int = 0,
    val idPlayer:Int=0,
    val idImage:Int=0
)
