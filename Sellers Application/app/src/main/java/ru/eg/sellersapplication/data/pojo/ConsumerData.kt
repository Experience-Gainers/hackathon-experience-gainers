package ru.eg.sellersapplication.data.pojo

import com.google.gson.annotations.SerializedName

data class ConsumerData(
    @SerializedName("articles") val status: List<Article>
)

data class Article(
    @SerializedName("some") val some:
)
