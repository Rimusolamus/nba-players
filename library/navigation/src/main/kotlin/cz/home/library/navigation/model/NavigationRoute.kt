package cz.home.library.navigation.model

interface NavigationRoute {
    /**
     * Returns the unique route name.
     */
    operator fun invoke(): String
}