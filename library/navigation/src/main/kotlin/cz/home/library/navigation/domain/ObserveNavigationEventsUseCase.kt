package cz.home.library.navigation.domain

import cz.home.library.navigation.model.NavigationEvent
import cz.home.nbaplayers.library.usecase.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

internal class ObserveNavigationEventsUseCase(
    private val navigationController: NavigationController
) : SynchronousUseCase<Unit, Flow<NavigationEvent>> {

    override fun invoke(input: Unit) = navigationController.navigationEvent
}