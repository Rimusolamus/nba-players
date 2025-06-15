package cz.home.nbaplayers.feature.playerdetails.model

import androidx.compose.runtime.Immutable

@Immutable
data class Player(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String,
    val height: String,
    val weight: String,
    val jerseyNumber: String,
    val college: String,
    val country: String,
    val draftYear: Int,
    val draftRound: Int,
    val draftNumber: Int,
    val team: Team
)