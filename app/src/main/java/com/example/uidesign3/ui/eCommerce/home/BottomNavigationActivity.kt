package com.example.uidesign3.ui.eCommerce.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ActivityBottomNavigation2Binding

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavigation2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBottomNavigation2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_Fragment, ExploreFragment())
            .commit()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.Explore -> ExploreFragment()
                R.id.Projects -> ExploreFragment()
                R.id.Inbox -> ExploreFragment()
                R.id.Profile -> ExploreFragment()
                else -> null
            }

            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_Fragment, it)
                    .commit()
            }
            true
        }
    }
}