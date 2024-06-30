package com.example.parkingnbeltran.view.myprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingnbeltran.R

//class VehiclePagerAdapter(private val vehicles: List<Vehicle>) : RecyclerView.Adapter<VehiclePagerAdapter.VehicleViewHolder>() {
//
//    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView: ImageView = itemView.findViewById(R.id.imageView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle_image, parent, false)
//        return VehicleViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
//        val vehicle = vehicles[position]
//        // Cargar la imagen del vehículo usando Glide
//        Glide.with(holder.itemView.context)
//            .load(vehicle.imgVehicle)
//            .into(holder.imageView)
//    }
//
//    override fun getItemCount(): Int = vehicles.size
//}
