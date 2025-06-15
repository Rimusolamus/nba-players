package cz.home.nbaplayers.feature.allplayers.model

import androidx.compose.runtime.Immutable

@Immutable
data class Player(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String,
    val team: Team
)