package com.mocma.notes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mocma.notes.data.NoteRepository
import com.mocma.notes.model.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {
    val notes = noteRepository.getAllNotes()
    var title by mutableStateOf("")
        private set
    var text by mutableStateOf("")
        private set
    var id by mutableStateOf(0L)
        private set
    var showDialog by mutableStateOf(false)

    fun upsertNote(note: NoteEntity) {
        viewModelScope.launch {
            noteRepository.upsertNote(note)
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    fun setNote(noteTitle: String = "", noteText: String = "", noteId: Long = 0L) {
        title = noteTitle
        text = noteText
        id = noteId
    }
}