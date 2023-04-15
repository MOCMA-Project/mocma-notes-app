package com.mocma.notes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NoteScreenViewModel: ViewModel() {
    var id by mutableStateOf(0L)
    var title by mutableStateOf("")
    var text by mutableStateOf("")
}