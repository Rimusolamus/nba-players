package cz.home.nbaplayers.feature.teamdetails.system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import cz.home.nbaplayers.feature.teamdetails.model.Team
import cz.home.nbaplayers.feature.teamdetails.presentation.TeamDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TeamDetailsScreen(
    viewModel: TeamDetailViewModel = koinViewModel<TeamDetailViewModel>()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    TeamScreen(state.value.team, isLoading = state.value.isLoading)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(team: Team?, isLoading: Boolean = false) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(team?.fullName ?: "Team detail") }
            )
        }
    ) { innerPadding ->
        if (team == null || isLoading) {
            ShimmerTeamPlaceholder(innerPadding)
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TeamCard(team)
            }
        }
    }
}

@Composable
fun TeamCard(team: Team) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = team.fullName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            InfoRow("Abbreviation", team.abbreviation)
            InfoRow("City", team.city)
            InfoRow("Name", team.name)
            InfoRow("Conference", team.conference)
            InfoRow("Division", team.division)
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
fun ShimmerTeamPlaceholder(
    innerPadding: PaddingValues
) {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
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