package com.example.news.data.model.news

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SourceResponse(

    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String
): Serializable