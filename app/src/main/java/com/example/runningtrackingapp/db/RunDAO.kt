package com.example.runningtrackingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RunDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run:Run)

    @Delete
    suspend fun deleteRun(run: Run)
    @Query("select * from running_table order by timeStamp desc")
    fun getAllRunsSortedByDate():LiveData<List<Run>>
    @Query("select * from running_table order by timeInMillis desc")
    fun getAllRunsSortedByTimeInMillis():LiveData<List<Run>>
    @Query("select * from running_table order by caloriesBurned desc")
    fun getAllRunsSortedByCaloriesBurned():LiveData<List<Run>>
    @Query("select * from running_table order by distanceInMeter desc")
    fun getAllRunsSortedByDistanceInMeter():LiveData<List<Run>>
    @Query("select * from running_table order by avgSpeedInKMH desc")
    fun getAllRunsSortedByAvgSpeedInKMH():LiveData<List<Run>>

    //statistics fragment
    @Query("select sum(timeInMillis) from running_table")
    fun getTotalTimeInMillis():LiveData<Long>
    @Query("select sum(caloriesBurned) from running_table")
    fun getTotalCaloriesBurned():LiveData<Int>
    @Query("select sum(distanceInMeter) from running_table")
    fun getTotalDistance():LiveData<Int>
    @Query("select avg(avgSpeedInKMH) from running_table")
    fun getTotalAvgSpeed():LiveData<Float>
}