package com.example.firstanimations.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.firstanimations.data.models.MatchEntity
import com.example.firstanimations.data.models.StadiumEntity
import com.example.firstanimations.data.models.TeamEntity
import com.example.firstanimations.data.models.relations.MatchAndStadium

@Dao
interface MatchDao {
    @Query("SELECT * FROM matchentity where date = :date ORDER BY date DESC")
    suspend fun getMatchesByDate(date:String):List<MatchAndStadium>

    @Query("SELECT * FROM matchentity ORDER BY date DESC")
    suspend fun getMatches():List<MatchAndStadium>

    @Insert(onConflict = REPLACE)
    suspend fun saveMatch(matchEntity: MatchEntity): Long

    @Query("SELECT * FROM matchentity ")
    suspend fun getMatchesSimplify(): List<MatchEntity>

}