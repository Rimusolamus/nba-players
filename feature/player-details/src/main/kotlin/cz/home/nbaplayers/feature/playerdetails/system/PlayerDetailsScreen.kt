package cz.home.nbaplayers.feature.playerdetails.system

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlayerDetailsScreen(
    playerId: Int,
    modifier: Modifier = Modifier
) {
    Text(text = "Detail for playerId: $playerId", modifier = modifier)
}