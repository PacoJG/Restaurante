package com.example.restaurante

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("{list}")
    fun listRestaurantes(@Path("list") list:String?): Call<List<Restaurant>>
}