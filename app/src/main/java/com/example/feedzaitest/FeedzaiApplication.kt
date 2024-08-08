package com.example.feedzaitest

import android.app.Application
import com.example.data.di.getModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import com.example.feedzaitest.di.modules


class FeedzaiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FeedzaiApplication)
            val dataModule = getModule(this@FeedzaiApplication)
            val presentationModules = modules
            val allDependencies = mutableListOf<Module>()
            allDependencies += dataModule
            allDependencies += presentationModules
            modules(allDependencies.toList())
        }
    }
}