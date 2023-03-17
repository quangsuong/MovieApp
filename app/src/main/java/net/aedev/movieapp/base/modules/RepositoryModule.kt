package net.aedev.movieapp.base.modules

import net.aedev.movieapp.base.repository.ApiRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ApiRepository(get(), get()) }
}
