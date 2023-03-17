package net.aedev.movieapp.base.modules

import net.aedev.movieapp.base.ultils.rx.AppReactivexSchedulers
import org.koin.dsl.module

val reactivexModule = module {
    single { AppReactivexSchedulers() }
}
