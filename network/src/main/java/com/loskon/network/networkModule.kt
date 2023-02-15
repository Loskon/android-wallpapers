package com.loskon.network

import com.loskon.network.api.PixabayApi
import com.loskon.network.source.NetworkDataSource
import com.loskon.network.utlis.NetworkUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideLoggingInterceptor() }
    single { provideOkHttp(get()) }
    single { provideRetrofit(get()) }
    single { providePixabayApi(get()) }

    single { NetworkUtil(androidContext()) }
    single { NetworkDataSource(get()) }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

@Suppress("MagicNumber")
private fun provideOkHttp(logging: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) addInterceptor(logging)
    }.build()
}

private fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttp)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

private fun providePixabayApi(retrofit: Retrofit): PixabayApi {
    return retrofit.create(PixabayApi::class.java)
}