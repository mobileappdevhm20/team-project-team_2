package com.mobileappdevelopment.themunichquiz.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobileappdevelopment.themunichquiz.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val navController = this.findNavController(R.id.myNavHostFragment)
        //NavigationUI.setupActionBarWithNavController(this, navController)
    }

    /**
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
    **/
}
