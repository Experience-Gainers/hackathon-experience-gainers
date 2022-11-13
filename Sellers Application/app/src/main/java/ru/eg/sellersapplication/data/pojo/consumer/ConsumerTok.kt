package ru.eg.sellersapplication.data.pojo.consumer

import com.google.gson.annotations.SerializedName

data class ConsumerTok(
    @SerializedName("value") val value: String,
    @SerializedName("expiredDate") val expDate: String
)
