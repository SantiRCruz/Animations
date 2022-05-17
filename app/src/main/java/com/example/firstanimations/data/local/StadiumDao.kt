package com.example.firstanimations.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import com.example.firstanimations.data.models.StadiumEntity

@Dao
interface StadiumDao {

    @Insert(onConflict = ABORT)
    suspend fun saveStadium(stadiumEntity: StadiumEntity):Long
}