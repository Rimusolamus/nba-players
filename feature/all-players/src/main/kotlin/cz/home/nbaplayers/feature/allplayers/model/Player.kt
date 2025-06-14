package cz.home.nbaplayers.feature.allplayers.model

data class Player(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val teamId: Int?, // nullable?
    val position: String?,
    val heightFeet: Int?,
    val heightInches: Int?,
    val weightPounds: Int?,
)