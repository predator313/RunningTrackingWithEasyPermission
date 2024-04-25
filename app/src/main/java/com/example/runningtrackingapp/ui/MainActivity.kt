package com.example.runningtrackingapp.ui
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.runningtrackingapp.R
import com.example.runningtrackingapp.databinding.ActivityMainBinding
import com.example.runningtrackingapp.utils.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToTrackingFragmentIfNeeded(intent)  //this wil happen when the main activity is destroyed
        //but the service is still running when we pass the pending intent the main activity is
        //started again

        //but if the main activity is already running then we need to handle this case also
        setSupportActionBar(binding.toolbar)


        // Retrieve NavController from NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Set up BottomNavigationView with NavController
        binding.bottomNavigationView.setupWithNavController(navController)

        // Set up Navigation Destination Change Listener
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.runFragment2, R.id.settingFragment, R.id.statisticsFragment ->{
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    Log.e("hello","bottom nav visible .......")

                }

                else ->{
                    binding.bottomNavigationView.visibility = View.GONE
                    Log.e("hello","bottom nav not visible...")
                }

            }
        }
    }
    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?){
        if(intent?.action== ACTION_SHOW_TRACKING_FRAGMENT){
            binding.navHostFragment.findNavController()
                .navigate(R.id.action_global_tracking_fragment)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //this will happen when the activity is already running and we send the pending intent
        navigateToTrackingFragmentIfNeeded(intent)
    }
}
