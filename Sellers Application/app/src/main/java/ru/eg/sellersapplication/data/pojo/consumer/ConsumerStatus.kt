package ru.eg.sellersapplication.data.pojo.consumer

import com.google.gson.annotations.SerializedName
import ru.eg.sellersapplication.data.pojo.Amount
import ru.eg.sellersapplication.data.pojo.Customer

data class ConsumerStatus(
    @SerializedName("amount") val amount: Amount,
    @SerializedName("customer") val customer: Customer
)

