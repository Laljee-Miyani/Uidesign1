package com.example.uidesign3.ui.onboarding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ActivityOnboardingIntroScreenBinding

class OnboardingIntroScreen : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingIntroScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardingIntroScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageList = ArrayList<SlideModel>()

        // Adding demo images
        imageList.add(SlideModel(R.drawable.img_onboarding, "Image 1"))
        imageList.add(SlideModel(R.drawable.img_onboarding, "Image 2"))
        imageList.add(SlideModel(R.drawable.img_onboarding, "Image 3"))
        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
    }
}