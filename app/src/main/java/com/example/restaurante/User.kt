package com.example.restaurante

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("Name") val username: String,
    @SerializedName("Email") val email: String,
    @SerializedName("Password") val password: String
)
