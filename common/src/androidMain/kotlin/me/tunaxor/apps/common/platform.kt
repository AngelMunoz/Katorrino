package me.tunaxor.apps.common

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material.Text
import androidx.core.net.toFile
import java.io.File

@Composable
actual fun SongFilePicker(onSelectSongs: (songs: List<Song>) -> Unit): Unit {

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
        onSelectSongs(
            it.mapNotNull { uri -> uri.path?.let { it1 -> File(it1) } }
                .map { file -> Song(file.name, file.canonicalPath) })
    }

    Button(onClick = {
        launcher.launch("audio/*")
    }) {
        Text("Select Files")
    }
}
