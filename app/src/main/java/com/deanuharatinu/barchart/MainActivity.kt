package com.deanuharatinu.barchart

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity
import com.deanuharatinu.barchart.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val scaleAnim = ScaleAnimation(
      1f, 1f,
      0f, 1f,
      Animation.ABSOLUTE, 0f,
      Animation.RELATIVE_TO_PARENT, 1f,
    )
    scaleAnim.duration = 600
    scaleAnim.interpolator = DecelerateInterpolator()
    scaleAnim.fillAfter = true
    scaleAnim.fillBefore = false
    scaleAnim.isFillEnabled = true

    binding.front.startAnimation(scaleAnim)
  }
}