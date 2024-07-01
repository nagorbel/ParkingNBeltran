package com.example.parkingnbeltran.view.register


import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.parkingnbeltran.databinding.ActivityRegisterBinding
import java.util.Objects


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Asignamos la vista/interfaz de registro
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        // Observamos los cambios en el LiveData del usuario
        registerViewModel.getUserLiveData().observe(this) { user ->
            if (user != null) {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT)
                    .show()
                registerViewModel.getUserLiveData().observe(this) { userLiveData ->
                    Toast.makeText(
                        this,
                        ("Bienvenido " + user.getDisplayName()).toString() + "!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // Observamos los cambios en el LiveData de errores
        registerViewModel.getErrorLiveData().observe(this) { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        // Agregamos un listener al botón de registro
        binding.btnRegister.setOnClickListener { v ->
            val name: String =
                Objects.requireNonNull(binding.nametext.getText()).toString()
                    .trim { it <= ' ' }
            val email: String =
                Objects.requireNonNull(binding.emailText.getText()).toString()
                    .trim { it <= ' ' }
            val password: String =
                Objects.requireNonNull(binding.passwordText.getText()).toString()
                    .trim { it <= ' ' }
            if (isValidName(name) && isValidEmail(email) && isValidPassword(password)) {
                registerViewModel.signUp(email, password, name)
            } else {
                showValidationError()
            }
        }
    }

    private fun isValidName(name: String?): Boolean {
        return name != null && !name.isEmpty()
    }

    private fun isValidEmail(email: String?): Boolean {
        return email != null && !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String?): Boolean {
        return password != null && password.length >= 6 // Ejemplo de longitud mínima
    }

    private fun showValidationError() {
        Toast.makeText(this, "Email o contraseña inválidos", Toast.LENGTH_SHORT).show()
    }
}