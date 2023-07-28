package com.example.translator.application

import android.app.Application
import com.example.translator.di.AppComponent
import com.example.translator.di.DaggerAppComponent

class TranslatorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .build()
    }

    companion object{
        lateinit var component: AppComponent
    }
}