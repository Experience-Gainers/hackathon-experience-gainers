package ru.eg.sellersapplication.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.eg.sellersapplication.data.pojo.SellerData

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

    //попытался реализовать запрос где третий шаг
    @POST("/bill")
    suspend fun postSellerData(sellerData: SellerData): Call<SellerData>
//    @GET("everything")
//    suspend fun getData(): SellerData

    companion object {
        fun create(url: String): SellerApi {
            val httpLogger = HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BASIC }

            val httpClient = OkHttpClient.Builder().addInterceptor(httpLogger).build()

            return Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SellerApi::class.java)
        }
    }
}