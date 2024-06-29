package com.example.parkingnbeltran.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
//import com.example.parkingnbeltran.domain.callback
//import com.example.parkingNBeltran.domain.Hora
//import com.example.parkingNBeltran.domain.Reserva


class DataRepository {
    private val mAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun registerUser(email: String?, password: String?): Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(email!!, password!!)
    }

    fun saveUserName(userId: String, name: String): Task<Void> {
        val user: MutableMap<String, Any> = HashMap()
        user["name"] = name
        return firestore.collection("users").document(userId).set(user)
    }

    val currentUser: FirebaseUser?
        get() = mAuth.currentUser

//    val tiposPlazas: MutableLiveData<List<String>?>
//        // Método para obtener tipos de plazas desde Firestore
//        get() {
//            val tiposLiveData = MutableLiveData<List<String>?>()
//            val tipos: MutableList<String> = ArrayList()
//
//            // Assuming "plazas" collection contains documents with a field "tipo"
//            firestore.collection("plazas")
//                .get()
//                .addOnSuccessListener { queryDocumentSnapshots ->
//                    for (documentSnapshot in queryDocumentSnapshots) {
//                        val tipo: String = documentSnapshot.getString("tipo")
//                        if (tipo != null) {
//                            tipos.add(tipo)
//                        }
//                    }
//                    tiposLiveData.setValue(tipos)
//                }
//                .addOnFailureListener { e -> tiposLiveData.setValue(null) }
//
//            return tiposLiveData
//        }

    // Método para verificar disponibilidad de horas
//    fun verificarDisponibilidadHora(
//        fecha: String?,
//        horaInicio: Hora?,
//        horaFin: Hora?
//    ): LiveData<Boolean> {
//        val disponibilidadLiveData = MutableLiveData<Boolean>()
//
//        // Aquí deberías implementar la lógica para verificar disponibilidad de horas
//        // Por simplicidad, vamos a devolver true siempre en este ejemplo
//        disponibilidadLiveData.value = true
//
//        return disponibilidadLiveData
//    }

    // Método para crear una reserva
//    fun crearReserva(reserva: Reserva): LiveData<Boolean> {
//        val successLiveData = MutableLiveData<Boolean>()
//
//        if ((reserva.getHoraFin().getHoraFin()) - (reserva.getHoraInicio()
//                .getHoraInicio()) > 9 * 60 * 60 * 1000
//        ) {
//            successLiveData.setValue(false) // Más de 9 horas
//            return successLiveData
//        }
//
//        if (reserva.getHoraInicio()
//                .getHoraInicio() > System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000
//        ) {
//            successLiveData.setValue(false) // Más de 7 días
//            return successLiveData
//        }
//
//        firestore.collection("reservas")
//            .add(reserva)
//            .addOnSuccessListener { documentReference -> successLiveData.setValue(true) }
//            .addOnFailureListener { e -> successLiveData.setValue(false) }
//
//        return successLiveData
//    }

    companion object {
        @get:Synchronized
        var instance: DataRepository? = null
            //Creación de la instancia en caso de que no exista.
            get() {
                if (field == null) {
                    field = DataRepository()
                }
                return field
            }
            private set
    }
}