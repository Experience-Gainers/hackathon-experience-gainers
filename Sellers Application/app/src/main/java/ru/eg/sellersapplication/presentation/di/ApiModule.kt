package ru.eg.sellersapplication.presentation.di

import org.koin.dsl.module
import ru.eg.sellersapplication.data.api.ConsumerApi
import ru.eg.sellersapplication.data.api.SellerApi
import ru.eg.sellersapplication.presentation.utils.constants.BASE_URL

/**
 * Модуль для апишек.
 * Обе апишки создаются один раз (single) способом внутри скобок (статик функция create)
 */
val apiModule = module {
    single<ConsumerApi> { ConsumerApi.create(BASE_URL) }
    single<SellerApi> { SellerApi.create(BASE_URL) }
}