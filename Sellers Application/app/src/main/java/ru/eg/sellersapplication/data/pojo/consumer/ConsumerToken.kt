package ru.eg.sellersapplication.data.pojo.consumer

import com.google.gson.annotations.SerializedName
import ru.eg.sellersapplication.data.pojo.Status

data class ConsumerToken(
    @SerializedName("requestId") val reqId: String,
    @SerializedName("status") val status: Status,
    @SerializedName("token") val tok: ConsumerTok
)