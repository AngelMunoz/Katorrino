package me.tunaxor.apps.common.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext

interface AboutComponent

class DefaultAboutComponent(componentContext: ComponentContext):
    AboutComponent, ComponentContext by componentContext


@Composable
fun AboutContent(component: AboutComponent) {
    Text("About")
}
