package cz.home.library.navigation.domain

import cz.home.library.navigation.model.NavigationEvent
import cz.home.library.navigation.model.NavigationRoute
import kotlinx.coroutines.flow.Flow

/**
 * Abstract navigation controller with base navigation logic.
 */
interface NavigationController {

    /**
     * Observable navigation events.
     */
    val navigationEvent: Flow<NavigationEvent>

    /**
     * Executes the navigation event.
     *
     * Note: Should pass the event into navigationEvent flow.
     */
    fun goTo(navigationEvent: NavigationEvent)

    /**
     * Executes the forward navigation event with given [route].
     */
    fun goTo(route: NavigationRoute): Unit = goTo(NavigationEvent.ForwardEvent(route))

    /**
     * Executes the back navigation event.
     */
    fun goBack(): Unit = goTo(NavigationEvent.BackEvent)
}