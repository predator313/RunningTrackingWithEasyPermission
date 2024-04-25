package com.example.runningtrackingapp.ui.fragment

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.runningtrackingapp.R
import com.example.runningtrackingapp.databinding.FragmentRunBinding
import com.example.runningtrackingapp.ui.viewmodel.MainViewModel
import com.example.runningtrackingapp.utils.REQUEST_CODE_BACKGROUND_LOCATION
import com.example.runningtrackingapp.utils.REQUEST_CODE_FOR_LOCATION_PERMISSION
import com.example.runningtrackingapp.utils.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class RunFragment : BindingFragment<FragmentRunBinding>(), EasyPermissions.PermissionCallbacks {
    private val viewModel: MainViewModel by viewModels()

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentRunBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_runFragment2_to_trackingFragment)
        }
    }



    private fun requestPermissions() {
        if (TrackingUtility.hasLocationPermission(requireContext())) {
            // Check if background permission is needed and not granted
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
                !TrackingUtility.hasBackgroundLocationPermission(requireContext())) {
                requestBackgroundLocationPermission()
            }
            // If all permissions are granted, just return
            return
        }

        // Requesting foreground location permissions
        requestForegroundLocationPermission()
    }

    private fun requestForegroundLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "You need to accept location permissions to use this app",
            REQUEST_CODE_FOR_LOCATION_PERMISSION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }


    private fun requestBackgroundLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "You need to allow background location",
            REQUEST_CODE_BACKGROUND_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        )
    }




    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    Log.e("hello", "all permission granted")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
            perms.containsAll(listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) &&
            !TrackingUtility.hasBackgroundLocationPermission(requireContext())) {
            requestBackgroundLocationPermission()
        }
}

override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
        AppSettingsDialog.Builder(this)
            .build()
            .show()
    } else {
        requestPermissions()
    }
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    //this is the Android framework function to handle the permission
    //since easy permission is not android framework function so we need to call function here
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    EasyPermissions.onRequestPermissionsResult(
        requestCode, permissions, grantResults, this
    )
}


}