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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import cz.home.nbaplayers.feature.allplayers.model.Player
import cz.home.nbaplayers.feature.allplayers.presentation.AllPlayersViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllPlayersScreen(
    onPlayerClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = koinViewModel<AllPlayersViewModel>()
    val players = viewModel.players.collectAsLazyPagingItems()

    AllPlayersScreenImpl(
        modifier = modifier,
        players = players,
        onPlayerClick = onPlayerClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AllPlayersScreenImpl(
    modifier: Modifier = Modifier,
    players: LazyPagingItems<Player>,
    onPlayerClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Nba players") },
            )
        }
    ) { innerPadding ->
        if (players.itemCount == 0) {
            PullToRefreshBox(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                isRefreshing = players.loadState.refresh is LoadState.Loading,
                onRefresh = { players.refresh() }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No players found")
                }
            }
        } else {
            PlayerList(
                players = players,
                onPlayerClick = onPlayerClick,
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun PlayerList(
    players: LazyPagingItems<Player>,
    onPlayerClick: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(players.itemCount) { index ->

            if (players[index] != null) {
                ListItem(
                    headlineContent = {
                        Row {
                            GlideImage(
                                model = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiEAVra7EKmhSuXR5x0vaYjrSRQj7cOPOCWjrperPk7p0SWbzT-qHhCTetEb6XklcUtAU09yhQ_1wBhboORXwok6HXSvNfc2QosleTDVCIfop_gRcwrDBTjj8prz26tpeAIfv8CAabOe_w/s0/Jumpman-Logo-Wallpaper-Feat-New-Symbol-Pic-Full-1920a80-.jpg",
                                contentDescription = "Player Image",
                                modifier = Modifier
                                    .width(64.dp)
                                    .height(64.dp)
                                    .align(Alignment.CenterVertically),
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .weight(1f)
                            ) {
                                players[index]?.let {
                                    players[index]?.let { it1 ->
                                        Text(
                                            text = "${it1.firstName} ${it.lastName}",
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                players[index]?.let {
                                    Text(
                                        text = "Position: ${it.position}",
                                    )
                                }
                            }
                            players[index]?.team?.let {
                                Text(
                                    text = it.name,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .animateItem()
                        .fillParentMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 0.dp)
                        .clickable { onPlayerClick(players[index]!!.id) }
                )
            }
        }
    }
}