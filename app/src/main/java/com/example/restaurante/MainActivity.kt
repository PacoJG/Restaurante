package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RestaurantAdapter.OnItemListener {

    private lateinit var adapter : RestaurantAdapter
    private val restaurantList = ArrayList<Restaurant>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = RestaurantAdapter(restaurantList, this)
        findViewById<RecyclerView>(R.id.rv_restaurant_list).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rv_restaurant_list).addItemDecoration(DividerItemDecoration(applicationContext,1))
        findViewById<RecyclerView>(R.id.rv_restaurant_list).adapter = adapter

        getAllRestaurants()

        /*val retrofit = Retrofit.Builder()
            .baseUrl("https://demo1400218.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)
        val restaurantList = service.listRestaurantes("Restaurantes")

        restaurantList.enqueue(object : Callback<List<Restaurant>>{
            override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                response.body()?.let { restaurantList ->
                    (rv_restaurant_list.adapter as RestaurantAdapter).setRestaurantList(restaurantList)
                }
            }
            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                call.cancel()
            }

        })*/
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://demo1400218.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAllRestaurants(){
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<RestaurantResponse> = getRetrofit().create(APIService::class.java).getAllRestaurants("Restaurantes")
            val allRestaurants:RestaurantResponse? = call.body()

            runOnUiThread {
                if(call.isSuccessful){
                    var restaurants: ArrayList<Restaurant> = (allRestaurants?.restaurantes ?: emptyArray<RestaurantResponse>()) as ArrayList<Restaurant>
                    restaurantList.clear()
                    restaurantList.addAll(restaurants)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun clickRestaurant(restaurant: Restaurant) {
        val intent = Intent(this,RestaurantDetails::class.java)
        intent.putExtra("nombre",restaurant.nombre)
        intent.putExtra("calificacion",restaurant.calificacion)
        intent.putExtra("año",restaurant.anio)
        intent.putExtra("costo_promedio",restaurant.costo)
        intent.putExtra("imagen_logo",restaurant.image)
        intent.putExtra("reseña",restaurant.resena)
        intent.putExtra("direccion",restaurant.direccion)
        intent.putExtra("imagen1",restaurant.imagen1)
        intent.putExtra("imagen2",restaurant.imagen2)
        intent.putExtra("imagen3",restaurant.imagen3)
        startActivity(intent)
    }


}