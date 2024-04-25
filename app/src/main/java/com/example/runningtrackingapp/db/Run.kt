package com.example.runningtrackingapp.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("running_table")
data class Run(
    var img:Bitmap?=null,
    var timeStamp:Long=0L,//when the run started
    var avgSpeedInKMH:Float=0f,
    var distanceInMeter:Int=0,
    var timeInMillis:Long=0L,  //how long the run remains
    var caloriesBurned:Int=0

){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
