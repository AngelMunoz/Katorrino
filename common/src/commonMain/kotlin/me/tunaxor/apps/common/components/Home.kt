package me.tunaxor.apps.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import me.tunaxor.apps.common.RouteConfig
import me.tunaxor.apps.common.Song
import me.tunaxor.apps.common.getFiles

interface HomeComponent {

    val songs: Value<List<Song>>
    fun navigateToPage(routeConfig: RouteConfig)

    fun getSongs()
}

class DefaultHomeComponent(
    componentContext: ComponentContext,
    private val goToPage: (routeConfig: RouteConfig) -> Unit
) :
    HomeComponent, ComponentContext by componentContext {

    override val songs = MutableValue(emptyList<Song>())

    override fun getSongs() {
        songs.value = getFiles()
    }
    override fun navigateToPage(routeConfig: RouteConfig) {
        goToPage(routeConfig)
    }
}

@Composable
fun HomeContent(component: HomeComponent) {
    val songs by component.songs.subscribeAsState()
    Column(modifier= Modifier.padding(12.dp)) {
        when (songs.isEmpty()) {
            true ->
                Text("No Songs Selected")
            else ->
                LazyColumn {
                    items(songs, key = { it.name }) {
                        Text(it.name)
                    }
                }
        }

        Button(onClick = {
            component.getSongs()
        }) {
            Text("Load Songs")
        }
    }

}
