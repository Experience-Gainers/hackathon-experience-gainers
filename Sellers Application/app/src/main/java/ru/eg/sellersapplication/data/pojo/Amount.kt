package ru.eg.sellersapplication.data.pojo

import com.google.gson.annotations.SerializedName

data class Amount(
    @SerializedName("currency") val currency: String,
    @SerializedName("value") val value: Double
)
