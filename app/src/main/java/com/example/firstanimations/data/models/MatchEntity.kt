package com.example.firstanimations.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    val idMatch: Int = 0,
    val homeTeamId:Int = 0,
    val awayTeamId:Int = 0,
    val homeScore:Int = 0,
    val awayScore:Int = 0,
    val homePenalties:Int = 0,
    val awayPenalties:Int = 0,
    val data:String = "",
    val stadiumId:Int = 0,
    val date: String = "",
    val hour: String = "",
    )