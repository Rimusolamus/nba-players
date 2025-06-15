package cz.home.nbaplayers.feature.allplayers.system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.home.nbaplayers.feature.allplayers.model.Player
import cz.home.nbaplayers.feature.allplayers.presentation.AllPlayersViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllPlayersScreen(
    onPlayerClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<AllPlayersViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    AllPlayersScreenImpl(
        modifier = modifier,
        players = state.value.players,
        isLoading = state.value.isLoading,
        onPlayerClick = onPlayerClick,
        onRefresh = viewModel::onRefresh,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AllPlayersScreenImpl(
    modifier: Modifier = Modifier,
    players: List<Player>?,
    isLoading: Boolean = false,
    onPlayerClick: (Int) -> Unit,
    onRefresh: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Nba players") },
            )
        }
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = isLoading,
            onRefresh = onRefresh,
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
        )
        {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                players?.size?.let {
                    items(it) { index ->
                        ListItem(
                            headlineContent = {
                                Row {
                                    Column(modifier = Modifier
                                        .padding(8.dp)
                                        .weight(1f)) {
                                        Text(
                                            text = "${players[index].firstName} ${players[index].lastName}",
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "Position: ${players[index].position}",
                                        )
                                    }
                                    Text(
                                        text = players[index].team.name,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            },
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary)
                                .animateItem()
                                .fillParentMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 0.dp)
                                .clickable { onPlayerClick(players[index].id) }
                        )
                    }
                }
            }
        }
    }
}


//@Preview
//@Composable
//private fun AllPlayersScreenPreview() {
//    AllPlayersScreenImpl(
//        players = listOf(
//            Player(
//                id = 1,
//                firstName = "John",
//                lastName = "Doe",
//                position = "Guard",
//                heightFeet = "",
//                team = Team(
//                    id = 1,
//                    fullName = "Los Angeles Lakers",
//                    abbreviation = "LAL",
//                    city = "Los Angeles",
//                    conference = "West",
//                    division = "Pacific",
//                    name = "Lakers"
//                )
//            ),
//            Player(
//                id = 2,
//                firstName = "Jane",
//                lastName = "Smith",
//                position = "Forward",
//                heightFeet = "6",
//                team = Team(
//                    id = 2,
//                    fullName = "Boston Celtics",
//                    abbreviation = "BOS",
//                    city = "Boston",
//                    conference = "East",
//                    division = "Atlantic",
//                    name = "Celtics"
//                )
//            ),
//            Player(
//                id = 3,
//                firstName = "Alice",
//                lastName = "Johnson",
//                position = "Center",
//                heightFeet = "6",
//                team = Team(
//                    id = 3,
//                    fullName = "Chicago Bulls",
//                    abbreviation = "CHI",
//                    city = "Chicago",
//                    conference = "East",
//                    division = "Central",
//                    name = "Bulls"
//                )
//            )
//        ),
//        onPlayerClick = {}
//    )
//}
