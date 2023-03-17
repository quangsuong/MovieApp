package net.aedev.movieapp.ui.main

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val mainappModules = module {
    loadKoinModules(mainActivityModule)
}
