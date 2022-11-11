package ru.eg.sellersapplication.presentation.di

import org.koin.dsl.module

val viewModelsModule = module {
    /* Так можно инжектить вьюмодел. Factory - создаются множественные компоненты
    factory<ViewModelName> { ViewModelName(someConstructor = get()) }

    Так создается единственный экзепляр класса SomeConstructor
    single<SomeConstructor> { SomeConstructor() }

    Метод get() ищет необходимый класс по зависимостям и если находит - внедряет, иначе кидает ошибку
    Koin инициализируется в Application классе. В нашем случае app это ApplicationModule,
    он также регистрируется в манифесте как name=".ApplicationModule"
     */
}