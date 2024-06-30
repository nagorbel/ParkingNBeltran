package com.example.parkingnbeltran.data


import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parkingnbeltran.domain.Booking
import com.example.parkingnbeltran.domain.Space
import com.example.parkingnbeltran.domain.SpaceType
import com.example.parkingnbeltran.domain.Vehicle
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class DataRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    val currentUser: FirebaseUser?
        get() = mAuth.currentUser

    val bookingsList = mutableListOf<Booking>()

    fun registerUser(email: String?, password: String?): Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(email!!, password!!)
    }

    fun saveUserName(userId: String, name: String): Task<Void> {
        val user: MutableMap<String, Any> = HashMap()
        user["name"] = name
        return firestore.collection("users").document(userId).set(user)
    }

    fun addBookingToFirestore(booking: Booking) {

        // Convert Booking object to a map
        val bookingMap = hashMapOf(
            "bookingId" to booking.bookingId,
            "date" to booking.date,
            "startingHour" to booking.startingHour,
            "endingHour" to booking.endingHour,
            "vehicleId" to booking.vehicleId,  // Assuming vehicleId is a String or some identifiable field
            "spaceId" to booking.spaceId       // Assuming spaceId is a String or some identifiable field
        )

//        firestore.collection("bookings").document("booking1")
//            .set(bookingMap)
//            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
//            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        val bookingsCollection = firestore.collection("bookings")

        bookingsCollection
            .add(bookingMap)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun getBookingsFromFirestore (){
        firestore.collection("booking")
            .get()
            .addOnSuccessListener { result ->
                bookingsList.clear()
                for (document in result) {
                    val booking = document.toObject(Booking::class.java).copy(
                        bookingId = document.getLong("bookingId")?.toInt() ?: 0,
                        date = document.getLong("date") ?: 0L,
                        startingHour = document.getLong("startingHour") ?: 0L,
                        endingHour = document.getLong("endingHour") ?: 0L,
                        vehicleId = document.get("vehicleId")?.let { vehicle ->
                            Vehicle(
                                vehicleId = (vehicle as Map<*, *>)["vehicleId"]?.toString()?.toInt() ?: 0,
                                imgVehicle = vehicle["imgVehicle"] as? String ?: ""
                            )
                        } ?: Vehicle(0, ""),
                        spaceId = document.get("spaceId")?.let { space ->
                            Space(
                                spaceId = (space as Map<*, *>)["spaceId"]?.toString()?.toInt() ?: 0,
                                type = SpaceType.valueOf(space["type"] as? String ?: "CAR")
                            )
                        } ?: Space(0, SpaceType.CAR)
                    )
                    bookingsList.add(booking)
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

    }



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

     //Método para crear una reserva
    fun crearReserva(reserva: Booking): LiveData<Boolean> {
        val successLiveData = MutableLiveData<Boolean>()

        if ((reserva.endingHour) - (reserva.startingHour) > 9 * 60 * 60 * 1000)
        {
            successLiveData.setValue(false) // Más de 9 horas
            return successLiveData
        }

        if (reserva.startingHour > System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)
        {
            successLiveData.setValue(false) // Más de 7 días
            return successLiveData
        }

        firestore.collection("reservas")
            .add(reserva)
            .addOnSuccessListener { documentReference -> successLiveData.setValue(true) }
            .addOnFailureListener { e -> successLiveData.setValue(false) }

        return successLiveData
    }

//    companion object {
//        @get:Synchronized
//        var instance: DataRepository? = null
//            //Creación de la instancia en caso de que no exista.
//            get() {
//                if (field == null) {
//                    field = DataRepository()
//                }
//                return field
//            }
//            private set
//    }

}