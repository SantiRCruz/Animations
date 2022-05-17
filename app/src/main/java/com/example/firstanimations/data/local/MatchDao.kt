package com.example.firstanimations.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.firstanimations.data.models.MatchEntity
import com.example.firstanimations.data.models.StadiumEntity
import com.example.firstanimations.data.models.TeamEntity
import com.example.firstanimations.data.models.relations.MatchAndStadium

@Dao
interface MatchDao {
//    @Query("SELECT * FROM matchentity " +
//            "JOIN teamentity ON matchentity.homeTeamId = teamentity.idTeam " +
//            "JOIN teamentity ON matchentity.awayTeamId = teamentity.idTeam " +
//            "JOIN stadiumentity ON stadiumId = idStadium ")
//    suspend fun getMatches():List<Map<MatchAndStadium,List<TeamEntity>>>

    @Insert(onConflict = ABORT)
    suspend fun saveMatch(matchEntity: MatchEntity):Long

    @Query("SELECT * FROM matchentity ")
    suspend fun getMatchesSimplify():List<MatchEntity>

}