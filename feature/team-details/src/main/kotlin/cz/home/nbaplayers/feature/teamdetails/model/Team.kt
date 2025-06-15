package cz.home.nbaplayers.feature.teamdetails.model

import androidx.compose.runtime.Immutable

@Immutable
data class Team(
    val id: Int,
    val conference: String,
    val division: String,
    val city: String,
    val name: String,
    val fullName: String,
    val abbreviation: String
)
