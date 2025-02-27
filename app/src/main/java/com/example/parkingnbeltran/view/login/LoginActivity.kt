package com.example.parkingnbeltran.view.login


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingnbeltran.MainActivity
import com.example.parkingnbeltran.databinding.ActivityLoginBinding
import com.example.parkingnbeltran.view.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Asignamos la vista/interfaz login (layout)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Acciones a realizar cuando el usuario clica el boton de login
        binding.loginButton.setOnClickListener { _ ->
            val email: String = binding.emailText.getText().toString()
            val password: String = binding.passwordText.getText().toString()
            loginViewModel.loginUser(email, password)
        }

        /*
        Acciones a realizar cuando el usuario clica el boton de crear cuenta (se cambia a pantalla de
        registro)
        */
        binding.createAccount.setOnClickListener { _ ->
            val intent =
                Intent(
                    this@LoginActivity,
                    RegisterActivity::class.java
                )
            startActivity(intent)
        }

        //Observamos la variable logged, la cual nos informara cuando el usuario intente hacer login y se
        //cambia de pantalla en caso de login correcto
        loginViewModel.isLogged().observe(this) { logged ->
            if (logged != null) {
                if (logged) {
                    //Login Correcto
                    val intent =
                        Intent(
                            this,
                            MainActivity::class.java
                        )
                    startActivity(intent)
                } else {
                    //Login incorrecto
                }
            }
        }
    }
}