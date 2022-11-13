package ru.eg.sellersapplication.data.mapper

import ru.eg.sellersapplication.data.pojo.consumer.ConsumerData
import ru.eg.sellersapplication.data.pojo.consumer.ConsumerPostSms

class ConsumerMapper {
    fun toConsumerData(reqId: String, phone: String, id: String): ConsumerData {
        return ConsumerData(reqId, phone, id)
    }

    fun toCode(reqId: String, code: String): ConsumerPostSms {
        return ConsumerPostSms(reqId, code)
    }
}