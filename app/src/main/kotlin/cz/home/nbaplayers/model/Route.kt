package cz.home.nbaplayers.model

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object AllPlayersNav: NavKey

@Serializable
data class PlayerDetailsNav(val playerId: Int): NavKey

@Serializable
data class TeamDetailsNav(val teamId: Int): NavKey