package com.example.runningtrackingapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.runningtrackingapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val repository: MainRepository
):ViewModel() {
}