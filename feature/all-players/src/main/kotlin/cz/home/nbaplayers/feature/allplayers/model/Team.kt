package cz.home.nbaplayers.feature.allplayers.model

import androidx.compose.runtime.Immutable

@Immutable
data class Team(
    val id: Int,
    val city: String,
    val name: String
)