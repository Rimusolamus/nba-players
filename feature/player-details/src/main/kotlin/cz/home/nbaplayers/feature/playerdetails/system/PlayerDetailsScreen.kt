package cz.home.nbaplayers.feature.playerdetails.system

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import cz.home.nbaplayers.feature.playerdetails.model.Player
import cz.home.nbaplayers.feature.playerdetails.presentation.PlayerDetailsViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlayerDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: PlayerDetailsViewModel = koinViewModel<PlayerDetailsViewModel>()
) {

    val state = viewModel.uiState.collectAsStateWithLifecycle()

    PlayerDetailsScreenImpl(
        modifier = modifier,
        playerDetails = state.value.player,
        isLoading = state.value.isLoading,
        onRefresh = viewModel::onRefresh,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDetailsScreenImpl(
    modifier: Modifier = Modifier,
    playerDetails: Player? = null,
    isLoading: Boolean = false,
    onRefresh: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Player Details") }
            )
        }
    ) { innerPadding ->

        if (isLoading || playerDetails == null) {
            ShimmerPlayerPlaceholder()
        } else {
            PlayerDetailContent(playerDetails, innerPadding)
        }
    }
}

@Composable
private fun PlayerDetailContent(player: Player, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PlayerCard(player)
    }
}

@Composable
fun PlayerCard(player: Player) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${player.firstName} ${player.lastName}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            InfoRow(label = "Position", value = player.position)
            InfoRow(label = "Height", value = player.height)
            InfoRow(label = "Weight", value = player.weight)
            InfoRow(label = "Jersey Number", value = player.jerseyNumber)
            InfoRow(label = "College", value = player.college)
            InfoRow(label = "Country", value = player.country)

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = "Draft Info",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            InfoRow(label = "Year", value = player.draftYear.toString())
            InfoRow(label = "Round", value = player.draftRound.toString())
            InfoRow(label = "Pick", value = player.draftNumber.toString())

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = "Team",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            InfoRow(label = "City", value = player.team.city)
            InfoRow(label = "Name", value = player.team.name)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        Text(
            text = value,
            fontSize = 14.sp
        )
    }
}

@Composable
fun ShimmerPlayerPlaceholder() {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(16.dp))
                .shimmer(shimmerInstance)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )
    }
}