package com.wj.learnmvi.base2

import android.util.Log
import com.wj.learnmvi.base2.impl.Main2Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val TAG = "RetrofitUtil"

object WanRetrofitClient {
    private const val BASE_URL = "https://www.wanandroid.com";

    val service by lazy { getService(Main2Api::class.java) }

    private var mRetrofit: Retrofit? = null

    private val mOkClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .followRedirects(false)
        /*.addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d(
            TAG,
            "log: $message"
        )
        }).setLevel(HttpLoggingInterceptor.Level.BODY))*/.build()


    fun <T> getService(serviceClass: Class<T>): T {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return mRetrofit!!.create(serviceClass)
    }
}