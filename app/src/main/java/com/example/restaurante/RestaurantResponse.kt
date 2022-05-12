package com.example.restaurante

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("Restaurante" ) var restaurantes : ArrayList<Restaurant> = arrayListOf()
)
