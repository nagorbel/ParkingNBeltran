package com.example.parkingnbeltran.domain

data class Space(
    val spaceId: Long,
    val type: SpaceType,
)

enum class SpaceType {
    CAR,
    ELECTRIC,
    MOTORCYCLE,
    DISABLED
}