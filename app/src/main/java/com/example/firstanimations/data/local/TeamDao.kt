package com.example.firstanimations.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.firstanimations.data.models.TeamEntity

@Dao
interface TeamDao {

    @Query("SELECT * FROM teamentity where nameTeam LIKE :search OR points LIKE :search  ")
    suspend fun getTeamsBySearch(search:String):List<TeamEntity>


    @Query("SELECT * FROM teamentity ORDER BY nameTeam ASC")
    suspend fun getTeamByNameAsc():List<TeamEntity>

    @Query("SELECT * FROM teamentity ORDER BY points ASC")
    suspend fun getTeamByPointsAsc():List<TeamEntity>

    @Query("SELECT * FROM teamentity")
    suspend fun getTeams():List<TeamEntity>


    @Insert(onConflict = ABORT)
    suspend fun saveTeam(teamEntity: TeamEntity):Long
}