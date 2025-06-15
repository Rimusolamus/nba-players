package cz.home.nbaplayers.system

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import cz.home.nbaplayers.feature.allplayers.system.AllPlayersScreen
import cz.home.nbaplayers.feature.playerdetails.system.PlayerDetailsScreen
import cz.home.nbaplayers.feature.teamdetails.system.TeamDetailsScreen
import cz.home.nbaplayers.model.AllPlayersNav
import cz.home.nbaplayers.model.PlayerDetailsNav
import cz.home.nbaplayers.model.TeamDetailsNav

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(AllPlayersNav)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is AllPlayersNav -> NavEntry(key = key) { AllPlayersScreen(
                    onPlayerClick = { playerId ->
                        backStack.add(PlayerDetailsNav(playerId))
                    },
                ) }
                is PlayerDetailsNav -> NavEntry(key = key) {
                    PlayerDetailsScreen(
                        playerId = key.playerId
                    )
                }
                is TeamDetailsNav -> NavEntry(key = key) {
                    TeamDetailsScreen(
                        teamId = key.teamId
                    )
                }
                else -> throw IllegalArgumentException("Unknown screen: $key")
            }
        }
    )
}