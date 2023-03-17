package me.tunaxor.apps.common

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import me.tunaxor.apps.common.components.AboutComponent
import me.tunaxor.apps.common.components.HomeComponent


/**
 * These are used in the Root component's view model to
 * know how to initialize a child component
 */
sealed class RouteTarget {
    class Home(val component: HomeComponent) : RouteTarget()
    class About(val component: AboutComponent) : RouteTarget()
}

/**
 * This is the "router configuration" which
 * can be used as a DU to stablish what pages
 * exist out there and if they have parameters
 */
sealed class RouteConfig: Parcelable {
    @Parcelize
    object Home: RouteConfig()

    @Parcelize
    object About: RouteConfig()
}
