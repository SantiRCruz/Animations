package com.example.firstanimations.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StadiumEntity(
    @PrimaryKey(autoGenerate = true)
    val idStadium:Int=0,
    val nameStadium:String="",
    val state:String=""
)
