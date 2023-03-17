package me.tunaxor.apps.common

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import me.tunaxor.apps.common.components.*


interface RootComponent {
    val childStack: Value<ChildStack<*, RouteTarget>>

    fun navigateTo(route: RouteConfig): Unit
}


class DefaultRootComponent(componentContext: ComponentContext) :
    RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<RouteConfig>()

    private val _childStack =
        childStack(
            source = navigation,
            initialConfiguration = RouteConfig.Home,
            handleBackButton = true, // Pop the back stack on back button press
            childFactory = ::createChild,
        )

    override val childStack: Value<ChildStack<*, RouteTarget>> = _childStack

    override fun navigateTo(route: RouteConfig): Unit {
        when (route) {
            is RouteConfig.Home ->
                navigation.bringToFront(RouteConfig.Home)

            is RouteConfig.About ->
                navigation.bringToFront(RouteConfig.About)
        }
    }

    private fun createChild(config: RouteConfig, componentContext: ComponentContext): RouteTarget =
        when (config) {
            is RouteConfig.Home -> RouteTarget.Home(home(componentContext))
            is RouteConfig.About -> RouteTarget.About(about(componentContext))
        }

    private fun home(componentContext: ComponentContext): HomeComponent =
        DefaultHomeComponent(
            componentContext = componentContext,
            goToPage = { navigateTo(it) }
        )

    private fun about(componentContext: ComponentContext): AboutComponent =
        DefaultAboutComponent(componentContext = componentContext)

}


@Composable
fun RootComponent(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) { child ->
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    IconButton(onClick = { component.navigateTo(RouteConfig.Home) }) {
                        Icon(Icons.Default.Home, "Home")
                    }

                    IconButton(onClick = { component.navigateTo(RouteConfig.About) }) {
                        Icon(Icons.Default.Info, "About")
                    }
                }
            }
        ) {
            when (val child = child.instance) {
                is RouteTarget.Home -> HomeContent(component = child.component)
                is RouteTarget.About -> AboutContent(component = child.component)
            }
        }
    }
}
