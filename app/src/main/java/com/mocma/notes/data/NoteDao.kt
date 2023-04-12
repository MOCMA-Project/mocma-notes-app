package com.mocma.notes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mocma.notes.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Upsert
    fun upsertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)
}