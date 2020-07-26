package com.jay.navermovie.module

import com.jay.navermovie.utils.NetWorkManager
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {
    single { NetWorkManager(get()) }
}