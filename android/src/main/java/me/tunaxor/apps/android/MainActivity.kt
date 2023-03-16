package me.tunaxor.apps.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import me.tunaxor.apps.common.DefaultRootComponent
import me.tunaxor.apps.common.RootComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Always create the root component outside Compose on the main thread
        val root =
            DefaultRootComponent(
                componentContext = defaultComponentContext(),
            )
        setContent {
            MaterialTheme {
                Surface {
                    RootComponent(component = root, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}
