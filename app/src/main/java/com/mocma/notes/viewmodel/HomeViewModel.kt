package com.mocma.notes.viewmodel

import androidx.lifecycle.ViewModel
import com.mocma.notes.data.NoteRepository
import com.mocma.notes.model.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {
    val notes = noteRepository.getAllNotes()

    fun upsertNote(note: NoteEntity) = noteRepository.upsertNote(note)

    fun deleteNote(note: NoteEntity)  = noteRepository.deleteNote(note)
}