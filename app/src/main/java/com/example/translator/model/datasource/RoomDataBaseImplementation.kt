package com.example.translator.model.datasource

import com.example.translator.model.data.DataModel


class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return listOf()
    }
}