package com.example.runningtrackingapp.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.runningtrackingapp.db.Run
import com.example.runningtrackingapp.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val runDao:RunDAO
) {
    suspend fun insertRun(run:Run){
        runDao.insertRun(run)
    }

    suspend fun deleteRun(run:Run){
        runDao.deleteRun(run)
    }

    fun getAllRunSortedByDate():LiveData<List<Run>>{
        return runDao.getAllRunsSortedByDate()
    }

    fun getAllRunsSortedByDistanceInMeter()=runDao.getAllRunsSortedByDistanceInMeter()
    fun getAllRunsSortedByTimesInMillis()=runDao.getAllRunsSortedByTimeInMillis()
    fun getAllRunsSortedByCaloriesBurned()=runDao.getAllRunsSortedByCaloriesBurned()
    fun getAllRunsSortedByAvgSpeedInKMH()=runDao.getAllRunsSortedByAvgSpeedInKMH()
    fun getAllRunsSortedByDate()=runDao.getAllRunsSortedByDate()

    //statistics fragment

    fun getTotalTimeInMillis()=runDao.getTotalTimeInMillis()
    fun getTotalCaloriesBurned()=runDao.getTotalCaloriesBurned()
    fun getTotalDistance()=runDao.getTotalDistance()
    fun getTotalAvgSpeed()=runDao.getTotalAvgSpeed()


}