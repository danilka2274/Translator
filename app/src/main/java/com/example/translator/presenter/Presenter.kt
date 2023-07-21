package com.example.translator.presenter

import com.example.translator.view.base.View
import com.example.translator.model.data.AppState

interface Presenter<V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}