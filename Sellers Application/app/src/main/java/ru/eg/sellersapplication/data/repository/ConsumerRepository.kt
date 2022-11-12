package ru.eg.sellersapplication.data.repository

import ru.eg.sellersapplication.data.api.ConsumerApi

/**
 * Класс через который вызываются запросы к серверу. Data-domain в Clean
 * Принимает API и через него вызывает запросы.
 */
class ConsumerRepository(
    private val consumerApi: ConsumerApi
) {
    /**
     * Запрос данных через апи
     * @param some Необходимые данные для запроса (если нужны)
     */
    suspend fun getData(some: Any): Any {
//        return consumerApi.getConsumerData()
        return "hello"
    }
}