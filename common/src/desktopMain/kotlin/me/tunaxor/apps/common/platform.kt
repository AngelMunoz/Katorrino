package me.tunaxor.apps.common

import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.filechooser.FileSystemView

actual fun getPlatformName(): String {
    return "Desktop"
}

actual fun getFiles(): List<Song> {
    val directory = FileSystemView.getFileSystemView().homeDirectory

    val dialog = JFileChooser(directory).apply {
        dialogTitle = "Select Your Music Files"
        isMultiSelectionEnabled = true
        fileFilter =
            FileNameExtensionFilter("Only MP3 Files", "mp3")
    }
    when (dialog.showOpenDialog(null)) {
        JFileChooser.APPROVE_OPTION ->
            return dialog.selectedFiles.map { Song(it.name, it.path) }
    }
    return emptyList()
}
