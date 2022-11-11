package ru.eg.sellersapplication.presentation

import android.app.Application
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.eg.sellersapplication.presentation.di.viewModelsModule

/**
 * Application class. Need to koin.
 * @param modules koin injection modules
 */
class ApplicationModule: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            modules(viewModelsModule) //Так объявляются модули, их пишем как vararg.
        }
    }
}