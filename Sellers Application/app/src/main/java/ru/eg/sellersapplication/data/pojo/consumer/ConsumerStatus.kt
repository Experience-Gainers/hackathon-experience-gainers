package ru.eg.sellersapplication.data.pojo.consumer

import com.google.gson.annotations.SerializedName
import ru.eg.sellersapplication.data.pojo.Customer
import ru.eg.sellersapplication.data.pojo.seller.AcceptanceData

data class ConsumerStatus(
    @SerializedName("amount") val amount: AcceptanceData.Amount,
    @SerializedName("customer") val customer: Customer
)

