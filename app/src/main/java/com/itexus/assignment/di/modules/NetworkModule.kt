package com.itexus.assignment.di.modules

import com.google.gson.GsonBuilder
import com.itexus.assignment.data.remote.jsonServer.JsonServerApi
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val HTTP_CLIENT_TIMEOUT = 30L
private const val BASE_URL = "https://my-json-server.typicode.com/SharminSirajudeen/test_resources/"

enum class ApiQualifier(val stringQualifier: StringQualifier) {
    JsonServer(StringQualifier("JsonServer"))
}

private inline fun <reified Api> provideApi(retrofit: Retrofit): Api =
    retrofit.create(Api::class.java)

private fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor,
    tokenAuthenticator: Authenticator? = null,
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(HTTP_CLIENT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(HTTP_CLIENT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(HTTP_CLIENT_TIMEOUT, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .apply {
            addInterceptor(interceptor)
            tokenAuthenticator?.let(::authenticator)
        }.build()
}

private fun provideRetrofit(
    client: OkHttpClient,
): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(createGsonFactory())
        .client(client)
        .baseUrl(BASE_URL)
        .build()
}

private fun createGsonFactory() = GsonConverterFactory.create(
    GsonBuilder()
        .setLenient()
        .create()
)

internal val retrofitModule = module {
    single(ApiQualifier.JsonServer.stringQualifier) {
        provideRetrofit(
            client = provideOkHttpClient(interceptor = get())
        )
    }
}

internal val apiModule = module {

    single<JsonServerApi> {
        provideApi(retrofit = get(ApiQualifier.JsonServer.stringQualifier))
    }
}

internal val networkModule = listOf(
    retrofitModule,
    apiModule,
)
