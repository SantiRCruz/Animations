package com.example.firstanimations.data.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.firstanimations.data.models.MatchEntity
import com.example.firstanimations.data.models.StadiumEntity
import com.example.firstanimations.data.models.TeamEntity

data class MatchAndStadium(
    @Embedded
    val match: MatchEntity,
    @Embedded
    val stadium: StadiumEntity,
    @Embedded
    val teamHome: TeamEntity,

)
