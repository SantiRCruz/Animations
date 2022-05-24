package com.example.firstanimations.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.firstanimations.data.models.PlayerEntity

@Dao
interface PlayerDao {

    @Query("SELECT * FROM playerentity WHERE team_id = :id_team ORDER BY num_jersey")
    suspend fun getPlayersOrderByJersey(id_team:Int):List<PlayerEntity>

    @Insert(onConflict = ABORT)
    suspend fun savePlayer(playerEntity: PlayerEntity):Long

}