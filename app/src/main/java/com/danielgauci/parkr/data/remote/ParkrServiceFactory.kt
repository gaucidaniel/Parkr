package com.danielgauci.parkr.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by daniel on 5/4/17.
 */
class ParkrServiceFactory {

    companion object{

        fun makeParkrService(): ParkrService{
            val okHttpClient = makeOkHttpClient()
            return makeParkrService(okHttpClient)
        }

        fun makeParkrService(okHttpClient: OkHttpClient): ParkrService{
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://parkr.danielgauci.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

            return retrofit.create(ParkrService::class.java)
        }

        fun makeOkHttpClient(): OkHttpClient{
            return OkHttpClient.Builder()
                    .build()
        }
    }
}