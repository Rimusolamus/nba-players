package cz.home.nbaplayers.feature.playerdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.home.nbaplayers.feature.playerdetails.domain.GetPlayerDetailsUseCase
import cz.home.nbaplayers.feature.playerdetails.model.Player
import cz.home.nbaplayers.library.data.infrastructure.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerDetailsViewModel(
    private val playerId: Int,
    private val getPlayerDetails: GetPlayerDetailsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State>
        get() = _uiState

    init {
        viewModelScope.launch {
            fetchPlayerDetails()
        }
    }

    private fun fetchPlayerDetails() {
        viewModelScope.launch {
            getPlayerDetails(playerId).collect { result ->
                when (result) {
                    is Data.Loading -> {
                        _uiState.value = State(isLoading = true)
                    }

                    is Data.Success -> {
                        _uiState.value = State(player = result.value, isLoading = false)
                    }

                    is Data.Error -> {
                        _uiState.value = State(error = result.exception, isLoading = false)
                    }
                }
            }
        }
    }

    data class State(
        val player: Player? = null,
        val isLoading: Boolean = false,
        val error: Throwable? = null
    )
}