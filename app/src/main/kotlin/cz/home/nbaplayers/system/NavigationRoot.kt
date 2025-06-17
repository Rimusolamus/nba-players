package cz.home.nbaplayers.system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
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
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(AllPlayersNav)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

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
                    is AllPlayersNav -> NavEntry(key = key) {
                        AllPlayersScreen(
                            onPlayerClick = { playerId ->
                                backStack.add(PlayerDetailsNav(playerId))
                            },
                        )
                    }

                    is PlayerDetailsNav -> NavEntry(key = key) {
                        PlayerDetailsScreen(
                            viewModel = koinViewModel {
                                parametersOf(key.playerId)
                            },
                            onTeamClick = { teamId ->
                                backStack.add(TeamDetailsNav(teamId))
                            },
                            onBack = {
                                backStack.removeLastOrNull()
                            }
                        )
                    }

                    is TeamDetailsNav -> NavEntry(key = key) {
                        TeamDetailsScreen(
                            viewModel = koinViewModel {
                                parametersOf(key.teamId)
                            },
                            onBack = {
                                backStack.removeLastOrNull()
                            }
                        )
                    }

                    else -> throw IllegalArgumentException("Unknown screen: $key")
                }
            }
        )
    }
}