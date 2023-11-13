package com.deanuharatinu.barchart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deanuharatinu.barchart.databinding.ActivityMainBinding
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.showAlignTop

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val barChartModels: MutableList<BarChartModel> = mutableListOf(
            BarChartModel("Sen", 50_000f, 1_000_000f),
            BarChartModel("Sel", 200_000f, 1_000_000f),
            BarChartModel("Rab", 123_000f, 1_000_000f),
            BarChartModel("Kam", 500_000f, 1_000_000f),
            BarChartModel("Jum", 691_000f, 1_000_000f),
            BarChartModel("Sab", 804_000f, 1_000_000f),
            BarChartModel("Min", 630_000f, 1_000_000f),
        )

        val balloon = Balloon.Builder(this)
            .setLayout(R.layout.item_baloon)
            .setTextColorResource(R.color.black)
            .setElevation(6)
            .setTextSize(15f)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setArrowSize(10)
            .setArrowPosition(0.5f)
            .setCornerRadius(8f)
            .setBackgroundColorResource(R.color.white)
            .setBalloonAnimation(BalloonAnimation.FADE)
            .setLifecycleOwner(this)
            .build()

        binding.barChart.setValue(barChartModels) { index, view ->
            balloon.dismiss()
            // TODO: valuenya sekarang berapa?
            view.showAlignTop(balloon, )
        }
    }
}