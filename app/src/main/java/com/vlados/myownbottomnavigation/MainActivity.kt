package com.vlados.myownbottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vlados.myownbottomnavigation.app.MyApp
import com.vlados.myownbottomnavigation.databinding.ActivityMainBinding
import com.vlados.myownbottomnavigation.databinding.RandomListBinding
import com.vlados.myownbottomnavigation.di.AppComponent
import com.vlados.myownbottomnavigation.di.DaggerAppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var bindingClass: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val navView: BottomNavigationView = bindingClass.bottomNavigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.bottom_navigation_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.zoo_list, R.id.workers_list
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}