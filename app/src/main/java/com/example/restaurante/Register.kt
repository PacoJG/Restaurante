package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<Button>(R.id.btnRegister2).setOnClickListener {
            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnLogin2).setOnClickListener {
            val intent = Intent(this,Login::class.java)
            this.startActivity(intent)
        }
    }
}