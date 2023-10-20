package com.example.quizey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class Dashboard : AppCompatActivity() {

    lateinit var  bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Home())
                        .commit()
                    true
                }
                R.id.result -> {
                    // Replace the current fragment with Fragment3
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Result())
                        .commit()
                    true
                }
                R.id.profile -> {


                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Profile())
                        .commit()
                    true
                }
                else -> false
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, Home())
            .commit()
    }
}