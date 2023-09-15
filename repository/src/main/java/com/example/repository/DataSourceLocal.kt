package com.example.repository

import com.example.model.AppState


interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}