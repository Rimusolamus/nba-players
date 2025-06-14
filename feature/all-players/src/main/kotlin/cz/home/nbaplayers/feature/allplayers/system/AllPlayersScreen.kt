package cz.home.nbaplayers.feature.allplayers.system

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AllPlayersScreen(
    onPlayerClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(text = "All Players Screen", modifier = modifier)
    Spacer(modifier = Modifier.height(32.dp))
    Button(onClick = { onPlayerClick(123) }) {
        Text(text = "Go to Player Details")
    }
}