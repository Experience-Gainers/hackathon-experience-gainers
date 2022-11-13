package ru.eg.sellersapplication.data.api

import android.service.autofill.UserData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.eg.sellersapplication.data.pojo.ConsumerData

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

    //попытался создать запрос на передачу accountId, phone, requestId
    @POST("/payer/token/init/{siteId}")
    suspend fun postConsumerData(@Body userData: UserData): Call<ConsumerData>

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