package net.aedev.movieapp.ui.main

import net.aedev.movieapp.ui.main.home.homeModule
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.core.context.loadKoinModules

val mainActivityModule = module {
    viewModel {
        MainViewModel(get())
    }
    loadKoinModules(homeModule)
}
