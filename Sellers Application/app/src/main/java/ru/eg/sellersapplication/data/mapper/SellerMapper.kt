package ru.eg.sellersapplication.data.mapper

import ru.eg.sellersapplication.data.pojo.Amount
import ru.eg.sellersapplication.data.pojo.Customer
import ru.eg.sellersapplication.data.pojo.seller.AcceptanceData
import ru.eg.sellersapplication.data.pojo.seller.PaymentMethod
import ru.eg.sellersapplication.presentation.utils.constants.MOCK_TOKEN_TYPE

class SellerMapper {
    fun toData(token: String, customerId: String, amount: Double): AcceptanceData {
        return AcceptanceData(
            Amount("RUB", amount),
            PaymentMethod(token, MOCK_TOKEN_TYPE),
            Customer(customerId)
        )
    }
}