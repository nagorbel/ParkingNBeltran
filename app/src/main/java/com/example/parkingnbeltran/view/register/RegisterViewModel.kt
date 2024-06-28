package com.example.parkingnbeltran.view.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkingnbeltran.data.DataRepository
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser


// Initialize Firebase Auth
class RegisterViewModel : ViewModel() {

    private val firebaseRepository: DataRepository = DataRepository()
    private val userLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()
    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getUserLiveData(): LiveData<FirebaseUser> {
        return userLiveData
    }

    fun getErrorLiveData(): LiveData<String> {
        return errorLiveData
    }

    fun signUp(email: String?, password: String?, name: String) {
        firebaseRepository.registerUser(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = firebaseRepository.currentUser

                    user?.let {
                        firebaseRepository.saveUserName(it.uid, name)
                            .addOnCompleteListener { saveTask ->
                                if (saveTask.isSuccessful) {
                                    userLiveData.setValue(it)
                                } else {
                                    errorLiveData.setValue(
                                        "Failed to save user name: ${saveTask.exception?.message}"
                                    )
                                }
                            }
                    }
                } else {
                    // If sign up fails, display a message to the user.
                    Log.i("NO FUNCIONA", "signUp")
                    val errorMessage = if (task.exception is FirebaseAuthException) {
                        (task.exception as FirebaseAuthException).errorCode
                    } else {
                        task.exception?.message
                    }
                    errorLiveData.setValue("Registration failed: $errorMessage")
                }
            }
    }
}