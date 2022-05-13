package com.example.restaurante

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_restaurant_details.view.*
import kotlinx.android.synthetic.main.item_restaurante.view.*

class RestaurantViewHolder(view: View, onItemListener: RestaurantAdapter.OnItemListener): RecyclerView.ViewHolder(view) , View.OnClickListener {

    private var tvTitleRestaurant: TextView = view.findViewById(R.id.tvTitleRestaurant)
    private var tvanio_apertura: TextView = view.findViewById(R.id.tvanio_apertura)
    private var tvCosto: TextView = view.findViewById(R.id.tvCosto)
    private var tvCalification: TextView = view.findViewById(R.id.tvCalification)

    private val onItemListener = onItemListener
    private lateinit var restaurant: Restaurant

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        onItemListener.clickRestaurant(restaurant)
    }

    fun bind(item: Restaurant){
        tvTitleRestaurant.text = item.nombre?.toString()
        tvanio_apertura.text = item.anio?.toString()
        tvCosto.text = item.costo?.toString()
        tvCalification.text = item.calificacion?.toString()
        var img = item.image
        Glide.with(itemView).load(img).into(itemView.item_image_carddescriptive)
        /*var img1 = item.imagen1
        Glide.with(itemView).load(img1).into(itemView.imageView2)
        var img2 = item.imagen2
        Glide.with(itemView).load(img2).into(itemView.imageView3)
        var img3 = item.imagen3
        Glide.with(itemView).load(img3).into(itemView.imageView4)*/
        //Picasso.get().load(text).into(iv)
        itemView.star.setImageResource(R.drawable.ic_star)
        //itemView.item_image_carddescriptive.setImageResource(R.drawable.restaurante)
        restaurant = item
    }

}