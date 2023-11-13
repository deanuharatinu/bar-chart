package com.deanuharatinu.barchart

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.deanuharatinu.barchart.databinding.BarLayoutBinding
import com.deanuharatinu.barchart.databinding.ItemBarChartBinding

class BarChart : ConstraintLayout {
    private lateinit var binding: BarLayoutBinding
    private val barChartViewList: MutableList<ItemBarChartBinding> = mutableListOf()

    constructor(
        context: Context
    ) : super(context) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        binding = BarLayoutBinding.inflate(LayoutInflater.from(context), this, true)

        barChartViewList.add(binding.line1)
        barChartViewList.add(binding.line2)
        barChartViewList.add(binding.line3)
        barChartViewList.add(binding.line4)
        barChartViewList.add(binding.line5)
        barChartViewList.add(binding.line6)
        barChartViewList.add(binding.line7)
    }

    private fun startAnimation(target: ProgressBar, value: Float, maxValue: Float) {
        if (maxValue < value) return

        val calculatedValue = (100 / maxValue) * value

        val objAnim = ObjectAnimator.ofInt(
            target,
            "progress",
            0,
            calculatedValue.toInt()
        ).apply {
            duration = 500
            startDelay = 200
            interpolator = DecelerateInterpolator()

        }
        objAnim.start()
    }

    fun setValue(
        barChartList: List<BarChartModel>,
        onClick: ((Int, View) -> Unit)? = null
    ) {
        var calculatedMaxValue = 0f

        barChartList.forEachIndexed { index, barChartModel ->
            if (index < barChartViewList.size) {
                calculatedMaxValue = if (barChartModel.maxValue > calculatedMaxValue) {
                    barChartModel.maxValue
                } else {
                    calculatedMaxValue
                }

                val itemBinding = barChartViewList[index]
                itemBinding.tvDesc.text = barChartModel.title
                startAnimation(
                    itemBinding.bar,
                    barChartModel.value,
                    barChartModel.maxValue
                )

                itemBinding.bar.setOnClickListener { view ->
                    onClick?.let { onClick(index, view) }
                }
            }
        }

        calculatedMaxValue /= 1_000_000f
        binding.topHorizontalLine.tvHorizontalLineDesc.text = "${calculatedMaxValue}Jt"
        binding.midHorizontalLine.tvHorizontalLineDesc.text =
            "${(calculatedMaxValue / 2)}Jt"
        binding.bottomHorizontalLine.tvHorizontalLineDesc.text = "0Jt"
    }
}

data class BarChartModel(
    val title: String,
    val value: Float,
    val maxValue: Float,
)
