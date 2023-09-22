package com.example.translator.room

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper

class HistoryDataBaseImpl : HistoryDataBase() {
    override fun clearAllTables() {
        
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun historyDao(): HistoryDao {
        TODO("Not yet implemented")
    }
}