package com.example.restaurante

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.rv_restaurant_list).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rv_restaurant_list).adapter = RestaurantAdapter()

        val retrofit = Retrofit.Builder()
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

        })
    }
}