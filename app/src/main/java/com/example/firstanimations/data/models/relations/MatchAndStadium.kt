package com.example.firstanimations.data.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.firstanimations.data.models.MatchEntity
import com.example.firstanimations.data.models.StadiumEntity
import com.example.firstanimations.data.models.TeamEntity

data class MatchAndStadium(
    @Embedded
    val match: MatchEntity,
    @Relation(
        parentColumn = "stadiumId",
        entityColumn = "idStadium"
    )
    val stadiumEntity: StadiumEntity,
    @Relation(
        parentColumn = "homeTeamId",
        entityColumn = "idTeam"
    )
    val homeTeamEntity: TeamEntity,
    @Relation(
        parentColumn = "awayTeamId",
        entityColumn = "idTeam"
    )
    val awayTeamEntity: TeamEntity

)
