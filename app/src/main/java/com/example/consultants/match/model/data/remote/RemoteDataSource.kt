package com.example.consultants.match.model.data.remote

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {

    val WRITE_TIMEOUT = 60
    val READ_TIMEOUT = 30
    val CONNECT_TIMEOUT = 30

    val RANDOM_USER_URL = "https://randomuser.me/api/"

    val randApi: RemoteService by lazy { buildRetrofitWithInterceptors().create(RemoteService::class.java) }

    private fun buildRetrofitWithInterceptors(): Retrofit {
        val okBuilder = OkHttpClient.Builder()
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.v(RemoteDataSource::class.java.simpleName, message)
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okBuilder.addInterceptor(loggingInterceptor)

        val okHttpClient = okBuilder.build()

        val builder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(RANDOM_USER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        return builder.build()
    }
}
