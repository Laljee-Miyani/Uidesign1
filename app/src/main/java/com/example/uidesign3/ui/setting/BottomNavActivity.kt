package com.example.uidesign3.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ActivityBottomNavBinding

class BottomNavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, ChatFragment())
            .commit()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.chat -> ChatFragment()
                R.id.friends -> FriendsFragment()
                R.id.settings -> SettingFragment()
                else -> null
            }

            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.flFragment, it)  // Use correct container ID
                    .commit()
            }
            true
        }
    }
}