package ru.eg.sellersapplication.data.pojo.consumer

import com.google.gson.annotations.SerializedName

data class ConsumerPostSms(
    @SerializedName("requestId") val reqId: String,
    @SerializedName("smsCode") val code: String
)