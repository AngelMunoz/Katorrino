package me.tunaxor.apps.common

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.filechooser.FileSystemView

@Composable
actual fun SongFilePicker(onSelectSongs: (songs: List<Song>) -> Unit) {
    Button(onClick = {
        val directory = FileSystemView.getFileSystemView().homeDirectory

        val dialog = JFileChooser(directory).apply {
            dialogTitle = "Select Your Music Files"
            isMultiSelectionEnabled = true
            fileFilter =
                FileNameExtensionFilter("Only MP3 Files", "mp3")
        }
        when (dialog.showOpenDialog(null)) {
            JFileChooser.APPROVE_OPTION ->
                onSelectSongs(dialog.selectedFiles.map { Song(it.name, it.path) })
            else -> onSelectSongs(emptyList())
        }
    }) {
        Text("Select Music Files")
    }
}
