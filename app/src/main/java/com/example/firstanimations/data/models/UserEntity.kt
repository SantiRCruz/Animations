package com.example.firstanimations.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = arrayOf(Index(value = arrayOf("email","name"), unique = true)))
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id_user:Int = 0,
    val name:String = "",
    val email:String = "",
    val last_name:String = "",
    val password:String = "",
    val profile:String = "",
    val blocked:Boolean = false,
)