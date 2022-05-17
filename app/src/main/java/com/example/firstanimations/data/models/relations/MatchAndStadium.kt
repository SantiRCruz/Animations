package com.example.firstanimations.data.models.relations

import com.example.firstanimations.data.models.MatchEntity

data class MatchAndStadium(
    val match : MatchEntity ?= null,
    val stadium: MatchAndStadium?= null,
)
