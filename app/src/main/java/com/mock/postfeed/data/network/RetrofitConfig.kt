package com.mock.postfeed.data.network

import com.mock.postfeed.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {
    private const val timeOutInSeconds = 15L

    private fun buildOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                }
                connectTimeout(timeOutInSeconds, TimeUnit.SECONDS)
                writeTimeout(timeOutInSeconds, TimeUnit.SECONDS)
                readTimeout(timeOutInSeconds, TimeUnit.SECONDS)
            }
            .build()
    }

    fun build(): Retrofit {
        return Retrofit.Builder()
            .client(buildOkHttpClient())
            .baseUrl(Endpoints.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}