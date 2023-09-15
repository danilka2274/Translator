package com.example.translator.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import com.example.translator.di.application
import com.example.translator.di.descriptionScreen
import com.example.translator.di.historyScreen
import com.example.translator.di.mainScreen

class TranslatorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen, descriptionScreen))
        }
    }
}