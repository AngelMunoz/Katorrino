package me.tunaxor.apps.common

import androidx.compose.runtime.Composable

@Composable
expect fun SongFilePicker(onSelectSongs: (songs: List<Song>) -> Unit)
