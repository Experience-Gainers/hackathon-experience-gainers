package ru.eg.sellersapplication.presentation.di

import org.koin.dsl.module
import ru.eg.sellersapplication.data.repository.ConsumerRepository
import ru.eg.sellersapplication.data.repository.SellerRepository

/**
 * Провайдер репозиториев. Репо создаются как синглтон, апишки берутся из koin.
 */
val repositoryModule = module {
    single<ConsumerRepository> { ConsumerRepository(consumerApi = get()) }
    single<SellerRepository> { SellerRepository(sellerApi = get()) }
}