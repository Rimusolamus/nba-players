package cz.home.library.navigation.model

sealed interface NavigationEvent {
    data class ForwardEvent(
        val route: NavigationRoute,
        val clearBackStack: Boolean = false
    ) : NavigationEvent

    data object BackEvent : NavigationEvent
}