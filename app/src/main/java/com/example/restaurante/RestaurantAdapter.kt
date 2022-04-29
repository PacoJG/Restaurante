package com.example.restaurante

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_restaurante.view.*

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.MyViewHolder>(){

    private var restaurantList : List<Restaurant>? = emptyList()

    fun setRestaurantList(restaurantList : List<Restaurant>){
        this.restaurantList = restaurantList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurante, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantAdapter.MyViewHolder, position: Int) {
        /*val restaurantList = restaurantList?.get(position)
        if (restaurantList != null) {
            holder.itemView.tvTitleRestaurant.text = restaurantList.nombre
            holder.itemView.tvCalification.text = restaurantList.calificacion
            holder.itemView.tvanio_apertura.text = restaurantList.anio
            holder.itemView.tvCosto.text = restaurantList.costo
            //Glide.with(holder.itemView).load(restaurantList.image).into(holder.itemView.item_image_carddescriptive)
            //holder.bind(restaurantList.image)
        }*/
        restaurantList?.let { holder.bind(it.get(position)) }

    }

    override fun getItemCount(): Int {
        return restaurantList!!.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        //val iv:ImageView = view.findViewById(R.id.item_image_carddescriptive)
        lateinit var text:String
        fun bind(restaurant : Restaurant){
            //Picasso.get().load(image).into(iv)
            itemView.tvTitleRestaurant.text = restaurant.nombre
            itemView.tvanio_apertura.text = restaurant.anio
            itemView.tvCosto.text = restaurant.costo
            itemView.tvCalification.text = restaurant.calificacion
            text = restaurant.image
            Log.i(text,"Mi mensaje")
            Glide.with(itemView).load(text).into(itemView.item_image_carddescriptive)
            //Picasso.get().load(text).into(iv)
            itemView.star.setImageResource(R.drawable.ic_star)
            //itemView.item_image_carddescriptive.setImageResource(R.drawable.restaurante)
        }
    }
}

