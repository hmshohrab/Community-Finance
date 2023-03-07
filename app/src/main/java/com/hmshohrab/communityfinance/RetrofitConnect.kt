package com.hmshohrab.communityfinance

import android.net.sip.SipErrorCode.TIME_OUT
import com.google.gson.Gson
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.hmshohrab.communityfinance.data.dataSource.category.CategoryApiService
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 07,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
object RetrofitConnect {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://27.147.135.164/")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .connectionPool(ConnectionPool(0, 1, TimeUnit.NANOSECONDS))
                .addInterceptor(getLogInterceptors())
                .followRedirects(false)
                .followSslRedirects(false)
                // .cache(cache)
                .build())
            .build()
    }
    private fun getLogInterceptors(): Interceptor {

        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    fun getCategoryApiService(): CategoryApiService {
        return getRetrofit().create(CategoryApiService::class.java)
    }
}