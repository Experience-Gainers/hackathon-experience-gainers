package ru.eg.sellersapplication.data.pojo.seller

import com.google.gson.annotations.SerializedName
import ru.eg.sellersapplication.data.pojo.Amount
import ru.eg.sellersapplication.data.pojo.Customer

data class AcceptanceData(
    @SerializedName("amount") val amount: Amount,
    @SerializedName("paymentMethod") val method: PaymentMethod,
    @SerializedName("customer") val customer: Customer
)

