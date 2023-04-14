package com.mocma.notes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NoteDialogViewModel: ViewModel() {
    var title by mutableStateOf("")
    var text by mutableStateOf("")
    var id by mutableStateOf(0L)

    fun setNote(noteTitle: String, noteText: String, noteId: Long) {
        title = noteTitle
        text = noteText
        id = noteId
    }
}