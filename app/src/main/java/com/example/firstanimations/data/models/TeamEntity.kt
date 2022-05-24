package com.example.firstanimations.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamEntity(
    @PrimaryKey(autoGenerate = true)
    val idTeam:Int = 0,
    val nameTeam:String ="",
    val flag:String="",
    val points :String = ""
)
