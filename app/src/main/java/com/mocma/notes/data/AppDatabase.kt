package com.mocma.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mocma.notes.model.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}