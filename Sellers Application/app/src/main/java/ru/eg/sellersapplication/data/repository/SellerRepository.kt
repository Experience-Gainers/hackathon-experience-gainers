package ru.eg.sellersapplication.data.repository

import ru.eg.sellersapplication.data.api.SellerApi

/**
 * Класс через который вызываются запросы к серверу. Data-domain в Clean
 * Принимает API и через него вызывает запросы.
 * А ещё тут все на корутинах (suspend модификатор)
 */
class SellerRepository(
    private val sellerApi: SellerApi
) {
    /**
     * Запрос данных через апи
     * @param some Необходимые данные для запроса (если нужны)
     */
    suspend fun getData(some: Any): Any {
        return sellerApi.getData()
    }
}