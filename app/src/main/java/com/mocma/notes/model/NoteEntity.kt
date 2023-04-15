package com.mocma.notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val text: String,
    @ColumnInfo(name = "created_at") var createdAt: Long = 0L,
    @ColumnInfo(name = "modified_at") var modifiedAt: Long = 0L,
)