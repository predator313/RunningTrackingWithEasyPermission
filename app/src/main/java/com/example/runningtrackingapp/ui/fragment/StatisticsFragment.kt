package com.example.runningtrackingapp.ui.fragment

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.example.runningtrackingapp.databinding.FragmentStatisticBinding
import com.example.runningtrackingapp.ui.viewmodel.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment:BindingFragment<FragmentStatisticBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentStatisticBinding::inflate



    private val viewModel:StatisticsViewModel by viewModels()
}