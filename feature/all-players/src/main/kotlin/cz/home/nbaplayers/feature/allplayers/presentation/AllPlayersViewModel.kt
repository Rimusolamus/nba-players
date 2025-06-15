package cz.home.nbaplayers.feature.allplayers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.home.nbaplayers.feature.allplayers.domain.GetAllPlayersUseCase
import cz.home.nbaplayers.feature.allplayers.model.Player
import cz.home.nbaplayers.library.data.infrastructure.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class AllPlayersViewModel(
    private val getAllPlayers: GetAllPlayersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State>
        get() = _uiState

    init {
        fetchPlayers()
    }

    fun onRefresh() {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            getAllPlayers().collect { result ->
                when (result) {
                    is Data.Loading -> {
                        _uiState.value = State(isLoading = true)
                    }

                    is Data.Success -> {
                        _uiState.value = State(players = result.value, isLoading = false)
                    }

                    is Data.Error -> {
                        _uiState.value = State(error = result.exception, isLoading = false)
                    }
                }
            }
        }
    }

    data class State(
        val players: List<Player>? = null,
        val isLoading: Boolean = false,
        val error: Throwable? = null
    )
}