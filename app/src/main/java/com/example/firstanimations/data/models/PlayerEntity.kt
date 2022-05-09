package com.example.firstanimations.data.models

import androidx.room.Entity

@Entity
data class PlayerEntity(
    val id_player:Int = 0,
    val name_player:String = "",
    val born:String = "",
    val position:String = "",
    val num_faults:Int = 0,
    val num_yellow_cards:Int = 0,
    val num_red_cards:Int = 0,
    val num_goals:Int = 0,
    val information:String = "",
    val num_jersey:String = "",
    val team_id:String = "",
)
