package com.mocma.notes.data

import com.mocma.notes.model.NoteEntity
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {
    fun getAllNotes() = noteDao.getAllNotes()

    suspend fun upsertNote(note: NoteEntity) = noteDao.upsertNote(note)

    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)
}