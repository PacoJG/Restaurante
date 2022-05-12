package com.example.restaurante

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("Usuarios") var users: List<User>
)
