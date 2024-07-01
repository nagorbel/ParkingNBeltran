package com.example.parkingnbeltran.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class LoginViewModel : ViewModel() {
    // Aquí puedes declarar los LiveData y métodos necesarios para la vista de inicio de sesión
    private var logged: MutableLiveData<Boolean> = MutableLiveData(false)
    private val executor: Executor = Executors.newSingleThreadExecutor()

    fun isLogged(): LiveData<Boolean> {
        return logged
    }

    // Initialize Firebase Auth
    private var mAuth = FirebaseAuth.getInstance()

    fun loginUser(email: String, password: String) {
        // Sign in an existing user

//        logged.postValue(true)
//        return

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(executor) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user: FirebaseUser? = mAuth.currentUser
                    logged.postValue(true)
                    Log.i("signIn", "OK")
                } else {
                    // If sign in fails, display a message to the user.
                    logged.postValue(false)
                    Log.e("signIn", "NOK")
                }
            }
    }
}
