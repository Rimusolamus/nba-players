package cz.home.nbaplayers.feature.teamdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.home.nbaplayers.feature.teamdetails.domain.GetTeamDetailUseCase
import cz.home.nbaplayers.feature.teamdetails.model.Team
import cz.home.nbaplayers.library.data.infrastructure.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class TeamDetailViewModel(
    private val getTeamDetailUseCase: GetTeamDetailUseCase,
    teamId: Int
): ViewModel() {
    private val _uiState = MutableStateFlow(State())
    val uiState: StateFlow<State>
        get() = _uiState

    init {
        fetchTeamDetails(teamId)
    }

    private fun fetchTeamDetails(teamId: Int) {
        viewModelScope.launch {
            getTeamDetailUseCase(teamId).collect { result ->
                when (result) {
                    is Data.Loading -> {
                        _uiState.value = State(isLoading = true)
                    }

                    is Data.Success -> {
                        _uiState.value = State(team = result.value, isLoading = false)
                    }

                    is Data.Error -> {
                        _uiState.value = State(error = result.exception, isLoading = false)
                    }
                }
            }
        }
    }

    data class State(
        val team: Team? = null,
        val isLoading: Boolean = false,
        val error: Throwable? = null
    )
}