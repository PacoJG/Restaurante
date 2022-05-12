package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.restaurante.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            this.startActivity(intent)
        }
    }

    private fun login() {
        if(validarLogin()){
            val email: EditText = binding.etEmail
            val password: EditText = binding.etPassword
            autenticar(email.text.toString(),password.text.toString())
        } else {
            Toast.makeText(this,"Rellene todos los campos",Toast.LENGTH_LONG).show()
        }
    }

    private fun autenticar(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<UserResponse> = getRetrofit().create(APIService::class.java).getUsers("User")
            val allUsers:UserResponse? = call.body()

            runOnUiThread {

                if(call.isSuccessful){
                    var users: List<User> = allUsers?.users?: emptyList<User>()
                    var bandera: Boolean = false
                    for(user in users)
                    {
                        if( email.toString() == user.email.toString() && password.toString() == user.password.toString() )
                        {
                            bandera = true
                            break
                        }
                    }
                    if( bandera ) {
                        init(true)
                    } else {
                        init(false)
                    }
                } else {
                    //Toast.makeText(this,"Ocurrio un error",Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    private fun init(b: Boolean) {
        if(b) {
            Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            this.startActivity(intent)
        } else {
            Toast.makeText(this,"Usuario no existe o la contraseña es incorrecta",Toast.LENGTH_SHORT).show()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo1400218.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun validarLogin(): Boolean {
        if(validarEmail() && validarPassword()) return true
        else return false
    }

    private fun validarEmail(): Boolean {
        if( binding.etEmail.text.toString() != "" ) return true
        else {
            binding.etEmail.error = "Ingrese su correo"
            binding.etEmail.requestFocus()
            return false
        }
    }

    private fun validarPassword(): Boolean {
        if( binding.etPassword.text.toString() != "" ) return true
        else {
            binding.etPassword.error = "Ingrese su contraseña"
            binding.etPassword.requestFocus()
            return false
        }
    }
}