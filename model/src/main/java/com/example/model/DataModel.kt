package com.example.model

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("text") val text: String?,
    @SerializedName("meanings") val meanings: List<Meanings>?,
)