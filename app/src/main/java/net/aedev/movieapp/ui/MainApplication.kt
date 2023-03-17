package net.aedev.movieapp.ui

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import net.aedev.movieapp.base.modules.apiModule
import net.aedev.movieapp.base.modules.reactivexModule
import net.aedev.movieapp.base.modules.repositoryModule
import net.aedev.movieapp.base.modules.sharedPreferencesModule
import net.aedev.movieapp.ui.main.mainappModules
import org.koin.core.logger.Level

class MainApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)

            koin.loadModules(
                listOf(
                    apiModule,
                    reactivexModule,
                    repositoryModule,
                    sharedPreferencesModule,
                    mainappModules
                )
            )
        }
    }
}
