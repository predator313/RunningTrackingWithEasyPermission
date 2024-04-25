package com.example.runningtrackingapp.ui.fragment

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.example.runningtrackingapp.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment:BindingFragment<FragmentSettingsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSettingsBinding::inflate
}