package com.yuefeng.base.net

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RFUtil {
    fun rf(baseurl: String): Retrofit {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.writeTimeout(10, TimeUnit.SECONDS)
        //添加统一请求头
        builder.addInterceptor {
            //request
            val request = it.request()
            //获取request的创建者builder
            val requestBuilder: Request.Builder = request.newBuilder()
            requestBuilder
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .addHeader("Content-Type", "application/json")
            val newRequest = requestBuilder.build()
            val proceed = it.proceed(newRequest)
            proceed
        }
        builder.addInterceptor(
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("result-->", it)
            }).setLevel(HttpLoggingInterceptor.Level.BODY)
        ).retryOnConnectionFailure(false)

        return Retrofit.Builder()
            .client(builder.build())
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
