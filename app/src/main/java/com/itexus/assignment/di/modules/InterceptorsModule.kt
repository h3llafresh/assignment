package com.itexus.assignment.di.modules

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

internal val interceptorsModule = module {
    factory { provideLoggingInterceptor() }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor.Logger { message -> Log.d("Logging Interceptor", "OkHttp -> $message") }
    return HttpLoggingInterceptor(logger)
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}
