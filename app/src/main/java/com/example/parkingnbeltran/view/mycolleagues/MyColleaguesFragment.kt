package com.example.parkingnbeltran.view.mycolleagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.parkingnbeltran.databinding.FragmentMycolleaguesBinding

class MyColleaguesFragment : Fragment() {

    private var _binding: FragmentMycolleaguesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myColleaguesViewModel =
            ViewModelProvider(this)[MyColleaguesViewModel::class.java]

        _binding = FragmentMycolleaguesBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        myColleaguesViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}