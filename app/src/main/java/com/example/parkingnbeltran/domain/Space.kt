package com.example.parkingnbeltran.domain

data class Space(
    val SpaceId: Int,
    val type: SpaceType,
)

enum class SpaceType {
    CAR,
    ELECTRIC,
    MOTORCYCLE,
    DISABLED
}