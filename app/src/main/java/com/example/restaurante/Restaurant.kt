package com.example.restaurante

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Restaurant(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("nombre") val nombre: String,
    @Expose @SerializedName("calificacion") val calificacion: String,
    @Expose @SerializedName("año") val anio: String,
    @Expose @SerializedName("costo_promedio") val costo: String,
    @Expose @SerializedName("imagen_logo") val image: String,
    @Expose @SerializedName("reseña") val resena: String,
    @Expose @SerializedName("direccion") val direccion: String
)
