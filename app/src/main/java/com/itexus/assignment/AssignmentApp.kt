package com.itexus.assignment

import android.app.Application
import com.itexus.assignment.di.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AssignmentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AssignmentApp)
            modules(appModule)
        }
    }
}
