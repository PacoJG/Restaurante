package com.example.restaurante

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_restaurant_details.*
import kotlinx.android.synthetic.main.item_restaurante.*
import kotlinx.android.synthetic.main.item_restaurante.view.*

class RestaurantDetails : AppCompatActivity() {

    private lateinit var name:String
    private lateinit var image: String
    private lateinit var image1: String
    private lateinit var image2: String
    private lateinit var image3: String
    private lateinit var direccion: String
    private lateinit var resena: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)
        if(savedInstanceState == null){
            val bundle = intent.extras

            name = if(bundle != null){
                bundle.getString("nombre","nombre")
            }else{
                savedInstanceState?.getSerializable("nombre") as String
            }

            image = if(bundle != null){
                bundle.getString("imagen_logo","image_logo")
            }else{
                savedInstanceState?.getSerializable("image_logo") as String
            }

            direccion = if(bundle != null){
                bundle.getString("direccion","direccion")
            }else{
                savedInstanceState?.getSerializable("direccion") as String
            }

            resena = if(bundle != null){
                bundle.getString("reseña","reseña")
            }else{
                savedInstanceState?.getSerializable("reseña") as String
            }

            image1 = if(bundle != null){
                bundle.getString("imagen1","imagen1")
            }else{
                savedInstanceState?.getSerializable("imagen1") as String
            }

            image2 = if(bundle != null){
                bundle.getString("imagen2","imagen2")
            }else{
                savedInstanceState?.getSerializable("imagen2") as String
            }

            image3 = if(bundle != null){
                bundle.getString("imagen3","imagen3")
            }else{
                savedInstanceState?.getSerializable("imagen3") as String
            }
        }

        findViewById<TextView>(R.id.tvName).text = name.toString()
        findViewById<TextView>(R.id.tvDireccion).text = direccion.toString()
        //Log.i(text,"Mi mensaje")
        var imageRestaurant = findViewById<ImageView>(R.id.ivRestaurant)
        Glide.with(this).load(image).into(imageRestaurant)
        findViewById<TextView>(R.id.tvOpinion).text = resena.toString()
        var photo2 = findViewById<ImageView>(R.id.imageView2)
        Glide.with(this).load(image1).into(photo2)
        var photo3 = findViewById<ImageView>(R.id.imageView3)
        Glide.with(this).load(image2).into(photo3)
        var photo4 = findViewById<ImageView>(R.id.imageView4)
        Glide.with(this).load(image3).into(photo4)
    }
}