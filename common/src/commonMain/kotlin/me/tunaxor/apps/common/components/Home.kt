package me.tunaxor.apps.common.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import me.tunaxor.apps.common.RouteConfig

interface HomeComponent {
    fun navigateToPage(routeConfig: RouteConfig): Unit
}

class DefaultHomeComponent(
    componentContext: ComponentContext,
    private val goToPage: (routeConfig: RouteConfig) -> Unit
) :
    HomeComponent, ComponentContext by componentContext {

    override fun navigateToPage(routeConfig: RouteConfig) {
        goToPage(routeConfig)
    }
}

@Composable
fun HomeContent(component: HomeComponent) {
    Text("Home")
}
