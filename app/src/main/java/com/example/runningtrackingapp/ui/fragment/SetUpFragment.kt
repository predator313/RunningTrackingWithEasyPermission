package com.example.runningtrackingapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.runningtrackingapp.R
import com.example.runningtrackingapp.databinding.FragmentSetupsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetUpFragment:BindingFragment<FragmentSetupsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSetupsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvContinue.setOnClickListener {
            findNavController().navigate(R.id.action_setUpFragment_to_runFragment2)
        }
    }
}