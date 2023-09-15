package com.example.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = arrayOf("word"), unique = true )])
class HistoryEntity (
    @PrimaryKey
    @ColumnInfo(name = "word")
    var word: String,
    @ColumnInfo("description")
    var description: String?
)