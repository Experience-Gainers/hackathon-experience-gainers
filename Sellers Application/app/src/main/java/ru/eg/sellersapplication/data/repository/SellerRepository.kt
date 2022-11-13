package ru.eg.sellersapplication.data.repository

import ru.eg.sellersapplication.data.api.SellerApi
import ru.eg.sellersapplication.data.mapper.SellerMapper

/**
 * Класс через который вызываются запросы к серверу. Data-domain в Clean
 * Принимает API и через него вызывает запросы.
 * А ещё тут все на корутинах (suspend модификатор)
 */
class SellerRepository(
    private val sellerApi: SellerApi,
    private val mapper: SellerMapper
) {
    suspend fun confirm(token: String, customerId: String, amount: Double){
        val data = mapper.toData(token, customerId, amount)
        sellerApi.acceptancePayment(data)
    }
}