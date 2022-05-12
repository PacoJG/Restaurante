package com.example.restaurante

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface APIService {
   // @GET("{list}")
    //fun listRestaurantes(@Path("list") list:String?): Call<List<Restaurant>>
    @GET
    suspend fun getAllRestaurants(@Url url: String): Response<RestaurantResponse>

    @GET
    suspend fun getUsers(@Url url: String): Response<UserResponse>
}