package com.newestaf.bongeuihighutil.neis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NeisRetrofitClient {
    private val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(NeisOpenApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val neisService: NeisService by lazy {
        retrofitClient.build().create(NeisService::class.java)
    }

}