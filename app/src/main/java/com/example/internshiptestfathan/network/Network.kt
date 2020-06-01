package com.example.internshiptestfathan.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    companion object{

        val URL = "https://dot-android-internship-test.web.app/"

        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getRetrofit() : ApiInterface{
            return retrofit.create(ApiInterface::class.java)
        }

    }

}