package com.example.translator.view.base

import com.example.translator.model.data.AppState


interface View {
    fun renderData(appState: AppState)
}