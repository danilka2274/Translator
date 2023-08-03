package com.example.translator.application

import android.app.Application
import com.example.translator.di.application
import com.example.translator.di.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application,mainScreen))
        }
    }
}