package ru.eg.sellersapplication.presentation.di

import org.koin.dsl.module

//Создал тестовый файл, чтобы не испортить прошлый, мало ли, тут все так хорошо расписано, спасибо за комменты!
//-------
//Как я понял, нужно создать репозиторий и интерфейс пользователя (тип какие операции он может
//выполнять, например, сканировать куар, или формировать или еще че то и т.д)
//если вдруг я дурачок, и я ошибся в понимании твоей просьбы, то прошу меня простить.
//-------

data class User(val name: String, val status: Boolean,val phoneNumber: String, val token: String)

interface UserRepository {
    fun makeToken(token: String): String
    fun isSeller(status: Boolean): Boolean
    fun isConsumer(status: Boolean): Boolean
}

class UserRepositoryImpl: UserRepository {
    override fun makeToken(token: String): String {
        //какая нибудь логика с формированием токена
        return token
    }

    override fun isConsumer(status: Boolean): Boolean {
        return status
    }

    override fun isSeller(status: Boolean): Boolean {
        return status
    }
}

val usersModuleTest = module {
    /* Так можно инжектить вьюмодел. Factory - создаются множественные компоненты
    factory<ViewModelName> { ViewModelName(someConstructor = get()) }

    Так создается единственный экзепляр класса SomeConstructor
    single<SomeConstructor> { SomeConstructor() }

    Метод get() ищет необходимый класс по зависимостям и если находит - внедряет, иначе кидает ошибку
    Koin инициализируется в Application классе. В нашем случае app это ApplicationModule,
    он также регистрируется в манифесте как name=".ApplicationModule"
     */

    single<UserRepository> { UserRepositoryImpl() }


}