package ru.eg.sellersapplication.presentation.di

import org.koin.dsl.module
import ru.eg.sellersapplication.presentation.ui.fragments.consumer.ViewModelConsumer

/**
 * Хранилище viewModel'ей. Создаются через factory
 */
val viewModelsModule = module {
    factory<ViewModelConsumer> { ViewModelConsumer(consumerRepository = get()) }
}