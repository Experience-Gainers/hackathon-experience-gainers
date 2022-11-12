package ru.eg.sellersapplication.data.api

import retrofit2.http.GET

/**
 * Интерфейс для Retrofit запросов на сервер. Запросы для продавца.
 * @param someName Так обозначаются параметры
 * @param SellerData Класс-pojo, в этот класс засовываются данные запроса (JSON to POJO)
 * @see getData
 */
interface SellerApi {

    /**
     * Функция, которая кидает запрос. Лучше посмотреть доку ретрофита для формирования запросов
     */
    @GET("endUrl")
    suspend fun getData(): SellerData
}