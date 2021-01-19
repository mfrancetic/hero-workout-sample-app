package com.mfrancetic.heroworkoutsampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.mfrancetic.heroworkoutsampleapp.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private lateinit var navController: NavController
private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var topLevelDestinations: Set<Int>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupNavControler()
    }

    private fun setupNavControler() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        topLevelDestinations = setOf(R.id.splashFragment, R.id.loginFragment)
        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations)
            .build()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (topLevelDestinations.contains(navController.currentDestination?.id)) {
            finish()
        } else {
            onSupportNavigateUp()
        }
    }
}