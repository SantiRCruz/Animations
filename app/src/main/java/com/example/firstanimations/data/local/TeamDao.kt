package com.example.firstanimations.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import com.example.firstanimations.data.models.TeamEntity

@Dao
interface TeamDao {
    @Insert(onConflict = ABORT)
    suspend fun saveTeam(teamEntity: TeamEntity):Long
}