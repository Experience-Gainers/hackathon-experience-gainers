package ru.eg.sellersapplication.data.pojo.consumer

import com.google.gson.annotations.SerializedName

data class AcceptStatus(
    @SerializedName("value") val value: String,
    @SerializedName("changedDateTime") val data: String
)
