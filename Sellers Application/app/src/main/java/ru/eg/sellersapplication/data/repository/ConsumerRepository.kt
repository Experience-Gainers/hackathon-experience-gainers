package ru.eg.sellersapplication.data.repository

import retrofit2.Response
import ru.eg.sellersapplication.data.api.ConsumerApi
import ru.eg.sellersapplication.data.mapper.ConsumerMapper
import ru.eg.sellersapplication.data.pojo.consumer.ConsumerData
import ru.eg.sellersapplication.data.pojo.consumer.ConsumerSms
import ru.eg.sellersapplication.data.pojo.consumer.ConsumerToken

/**
 * Класс через который вызываются запросы к серверу. Data-domain в Clean
 * Принимает API и через него вызывает запросы.
 */
class ConsumerRepository(
    private val consumerApi: ConsumerApi,
    private val mapper: ConsumerMapper
) {
    suspend fun postConsumerData(
        merchId: String,
        reqId: String,
        phone: String,
        id: String
    ): Response<ConsumerSms> {
        val data = mapper.toConsumerData(reqId, phone, id)
        return consumerApi.postConsumerData(merchId, data)
    }

    suspend fun postCode(
        merchId: String,
        id: String,
        reqId: String,
        code: String
    ): Response<ConsumerToken> {
        val data = mapper.toCode(reqId, code)
        return consumerApi.postCode(merchId, id, data)
    }
}