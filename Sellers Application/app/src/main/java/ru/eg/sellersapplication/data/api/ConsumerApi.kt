package ru.eg.sellersapplication.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import ru.eg.sellersapplication.data.pojo.consumer.*

/**
 * Интерфейс для Retrofit запросов на сервер. Запросы для покупателя.
 * @param someName Так обозначаются параметры
 * @param SellerData Класс-pojo, в этот класс засовываются данные запроса (JSON to POJO)
 * @see getData
 */
interface ConsumerApi {

    /**
     * POST на сервер для перехода к отправке смс
     */
    @POST("payer/token/init/{siteId}")
    suspend fun postConsumerData(
        @Path("siteId") merchId: String,
        @Body data: ConsumerData
    ): Response<ConsumerSms>

    /**
     * POST на сервер для отправки кода из SMS
     */
    @POST("payer/token/confirm/{siteId}")
    suspend fun postCode(
        @Path("siteId") merchId: String,
        @Header("x-account-id") header: String,
        @Body data: ConsumerPostSms
    ): Response<ConsumerToken>

    /**
     * GET запрос на получение статуса, ждем пока продавец не отсканирует QR
     */
    @GET("bill/{reqId}")
    suspend fun getStatus(@Path("reqId") reqId: String): ConsumerStatus

    /**
     * GET на отправку подтверждения платежа
     */
    @GET("bill/{reqId}/pay")
    suspend fun accept(@Path("reqId") reqId: String): ConsumerAccept

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