package cz.home.nbaplayers.device

import cz.home.library.navigation.domain.NavigationController
import cz.home.library.navigation.model.NavigationEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class GlobalNavigationController: NavigationController {

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = EXTRA_BUFFER_CAPACITY)
    override val navigationEvent: Flow<NavigationEvent> = _navigationEvent.asSharedFlow()

    override fun goTo(navigationEvent: NavigationEvent) {
        _navigationEvent.tryEmit(navigationEvent)
    }

    override fun goBack() = goTo(BackNavigationEvent)

    companion object {
        // Note: The capacity is greater than 1 to achieve the possibility of calling another navigation event before the previous one is handled.
        private const val EXTRA_BUFFER_CAPACITY = 5
    }
}

internal typealias BackNavigationEvent = NavigationEvent.BackEvent