package com.test.projecttest.retrofit

import com.test.projecttest.retrofit.service.EventsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val PATH = "http://5f5a8f24d44d640016169133.mockapi.io/api/"
private const val MSG_ERROR = "Requisição não sucedida"

class AppRetrofit {

    private val client by lazy {
        val interceptador = HttpLoggingInterceptor()
        interceptador.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptador)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun <T> callRequisition(
        call: Call<T>,
        isSuccess: (itemList: T?) -> Unit,
        isFail: (error: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    isSuccess(response.body())
                } else {
                    isFail(MSG_ERROR)
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                isFail(t.message)
            }
        })
    }

    val serviceService: EventsService by lazy {
        retrofit.create(EventsService::class.java)
    }
}