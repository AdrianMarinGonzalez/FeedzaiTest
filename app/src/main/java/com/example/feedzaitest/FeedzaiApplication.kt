package com.example.feedzaitest

import android.app.Application
import com.example.data.di.getModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module


class FeedzaiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FeedzaiApplication)
            val dataModule = getModule(this@FeedzaiApplication)
            val allDependencies = mutableListOf<Module>()
            allDependencies += dataModule
            modules(allDependencies.toList())
        }
    }
}