package me.tunaxor.apps.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext

interface RootComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {}


@Composable
fun RootComponent(component: RootComponent, modifier: Modifier = Modifier) {
    Text("Olv")
}
