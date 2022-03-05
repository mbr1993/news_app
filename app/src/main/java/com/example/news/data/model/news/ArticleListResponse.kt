package com.example.news.data.model.news

import com.google.gson.annotations.SerializedName

data class ArticleListResponse(

    @SerializedName("articles")
    val articles: List<ArticleResponse>,

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int
)