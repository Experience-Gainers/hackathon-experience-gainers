package ru.eg.sellersapplication.data.pojo.consumer

import com.google.gson.annotations.SerializedName

data class ConsumerData(
    @SerializedName("requestId") val reqID: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("accountId") val accId: String
)
