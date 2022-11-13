package ru.eg.sellersapplication.data.pojo

import com.google.gson.annotations.SerializedName

class SellerData {
    class Amount(
        @SerializedName("currency") val currency: String,
        @SerializedName("value") val value: Double, //Int?
    )
    class PaymentMethod(
        @SerializedName("paymentToken") val paymentToken: String,
        @SerializedName("type") val type: String
    )
    class Customer(
        @SerializedName("account") val account: String
    )
}