package ru.eg.sellersapplication.data.pojo.consumer

import com.google.gson.annotations.SerializedName
import ru.eg.sellersapplication.data.pojo.Amount
import ru.eg.sellersapplication.data.pojo.Customer

data class ConsumerAccept(
    @SerializedName("billId") val billId: String,
    @SerializedName("amount") val amount: Amount,
    @SerializedName("status") val status: AcceptStatus,
    @SerializedName("customer") val customer: Customer
)
