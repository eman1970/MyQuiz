package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        Log.i(LOG_TAG,"onCreate")

        if (savedInstanceState != null) return

        supportFragmentManager.commit {
            add<HomeFragment>(R.id.container, null)
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_home -> goToHome()

                R.id.action_shop -> goToShop()

                else -> false
            }
        }
    }

    private fun goToShop(): Boolean {
        supportFragmentManager.commit {
            replace<QuizFragment>(R.id.container, null, null)
        }

        return true
    }

    private fun goToHome(): Boolean {
        supportFragmentManager.commit {
            replace<HomeFragment>(R.id.container, null, null)
        }

        return true
    }

}