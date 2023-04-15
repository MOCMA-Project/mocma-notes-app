package com.mocma.notes.viewmodel

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

    fun upsertNote(note: NoteEntity) {
        val timestamp = System.currentTimeMillis()
        if (note.createdAt == 0L) {
            note.createdAt = timestamp
        }
        note.modifiedAt = timestamp
        viewModelScope.launch {
            noteRepository.upsertNote(note)
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}