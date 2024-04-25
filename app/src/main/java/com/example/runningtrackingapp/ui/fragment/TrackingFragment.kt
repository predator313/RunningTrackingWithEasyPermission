package com.example.runningtrackingapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.example.runningtrackingapp.databinding.FragmentTrackingBinding
import com.example.runningtrackingapp.service.TrackingService
import com.example.runningtrackingapp.service.polyLine
import com.example.runningtrackingapp.ui.viewmodel.MainViewModel
import com.example.runningtrackingapp.utils.ACTION_START_OR_RESUME_SERVICE
import com.example.runningtrackingapp.utils.MAP_ZOOM
import com.example.runningtrackingapp.utils.POLYLINE_COLOR
import com.example.runningtrackingapp.utils.POLYLINE_WIDTH
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment:BindingFragment<FragmentTrackingBinding>() {
    private val viewModel:MainViewModel by viewModels()

    private var isTracking:Boolean=false
    private var pathPoints= mutableListOf<polyLine>()
    private var map:GoogleMap?=null
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentTrackingBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.onCreate(savedInstanceState)
        binding.btnToggleRun.setOnClickListener {
            sendCommandToTrackingService(ACTION_START_OR_RESUME_SERVICE)
        }
        binding.mapView?.getMapAsync{
            map=it
        }


    }
    private fun moveCameraToUser(){
        if(pathPoints.isNotEmpty() && pathPoints.last().isNotEmpty()){
            map?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    pathPoints.last().last(),
                    MAP_ZOOM

                )
            )
        }else{
            val delhi= LatLng(28.56, 77.29)
            map?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(delhi,15f)
            )
        }
    }
    private fun zoomToSeeWholeTrack(){
        val bounds= LatLngBounds.builder()
        for(ployline in pathPoints){
            for(pos in ployline){
                bounds.include(pos)
            }
        }
        map?.moveCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds.build(),
                binding.mapView.width,
               binding. mapView.height,
                (binding.mapView.height* 0.05f).toInt()

            )
        )
    }
    private fun addAllPolyLines(){
        //this fun help when the device is rotated
        for(polyLine in pathPoints){
            val polylineOptions=PolylineOptions()
                .color(POLYLINE_COLOR)
                .width(POLYLINE_WIDTH)
                .addAll(polyLine
                )
            map?.addPolyline(polylineOptions)

        }
    }

    private fun addLatestPolyLine(){
        if(pathPoints.isNotEmpty() && pathPoints.last().size>1){
            val preLastLatLng=pathPoints.last()[pathPoints.last().size-2]
            val lastLatLng=pathPoints.last().last()
            val polylineOptions=PolylineOptions()
                .color(POLYLINE_COLOR)
                .width(POLYLINE_WIDTH)
                .add(preLastLatLng)
                .add(lastLatLng)
            map?.addPolyline(polylineOptions)


        }
    }
    private fun sendCommandToTrackingService(action:String){
        Intent(requireContext(),TrackingService::class.java).also {
            it.action=action
            requireContext().startService(it)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView?.onLowMemory()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        binding.mapView?.onDestroy()
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //since we get the map asynchronously
        //onSaveInstanceState helps to cache the map
        binding.mapView?.onSaveInstanceState(outState)
    }
}