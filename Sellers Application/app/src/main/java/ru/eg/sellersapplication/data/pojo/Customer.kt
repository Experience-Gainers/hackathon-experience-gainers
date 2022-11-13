package ru.eg.sellersapplication.data.pojo

import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("account") val account: String
)