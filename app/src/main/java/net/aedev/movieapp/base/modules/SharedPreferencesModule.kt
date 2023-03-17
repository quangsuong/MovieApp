package net.aedev.movieapp.base.modules

import net.aedev.movieapp.base.local.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPreferencesModule = module {
    single { AppPreferences(androidContext()) }
}
