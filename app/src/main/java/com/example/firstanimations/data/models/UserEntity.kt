package com.example.firstanimations.data.models

import androidx.room.Entity

@Entity
data class UserEntity (
    val id_user:Int = 0,
    val name:String = "",
    val email:String = "",
    val last_name:String = "",
    val password:String = "",
    val profile:String = "",
    val blocked:Boolean = false,
)