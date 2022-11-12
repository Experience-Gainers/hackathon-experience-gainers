package ru.eg.sellersapplication.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Интерфейс для Retrofit запросов на сервер. Запросы для покупателя.
 * @param someName Так обозначаются параметры
 * @param SellerData Класс-pojo, в этот класс засовываются данные запроса (JSON to POJO)
 * @see getData
 */
interface ConsumerApi {

    /**
     * Функция, которая кидает запрос. Лучше посмотреть доку ретрофита для формирования запросов
     */
//    @GET("endUrl")
//    suspend fun getConsumerData(): ConsumerData

    companion object {
        fun create(url: String): ConsumerApi {
            val httpLogger = HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BASIC }

            val httpClient = OkHttpClient.Builder().addInterceptor(httpLogger).build()

            return Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ConsumerApi::class.java)
        }
    }
}