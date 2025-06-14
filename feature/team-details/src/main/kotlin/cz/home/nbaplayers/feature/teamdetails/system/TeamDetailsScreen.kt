package cz.home.nbaplayers.feature.teamdetails.system

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TeamDetailsScreen(
    teamId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Detail for teamId: $teamId",
        modifier = modifier
    )
}