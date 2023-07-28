package com.example.translator.model.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.translator.model.data.DataModel

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}