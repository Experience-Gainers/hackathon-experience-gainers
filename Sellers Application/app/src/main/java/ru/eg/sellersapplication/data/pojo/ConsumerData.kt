package ru.eg.sellersapplication.data.pojo

import com.google.gson.annotations.SerializedName

data class ConsumerData(
    @SerializedName("requestId") val requestId: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("accountId") val accountId: String
)