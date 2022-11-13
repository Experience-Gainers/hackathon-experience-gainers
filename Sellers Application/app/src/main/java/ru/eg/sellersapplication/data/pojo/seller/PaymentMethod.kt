package ru.eg.sellersapplication.data.pojo.seller

import com.google.gson.annotations.SerializedName
import ru.eg.sellersapplication.presentation.utils.constants.MOCK_TOKEN_TYPE

data class PaymentMethod(
    @SerializedName("paymentToken") val token: String,
    @SerializedName("type") val type: String = MOCK_TOKEN_TYPE
)