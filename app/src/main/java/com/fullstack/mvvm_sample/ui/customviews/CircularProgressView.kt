package com.fullstack.mvvm_sample.ui.customviews

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.fullstack.mvvm_sample.R

class CircularProgressView(
    context: Context?,
    attrs: AttributeSet?
) : View(context, attrs) {

    private var stokeWidth = 40f
    private var ovalSize = 150f
    private val ovalSpace = RectF()
    private var parentArcColor = Color.GRAY
    private var fillArcColor = Color.BLACK
    private val ARC_FULL_ROTATION_DEGREE = 360.0
    private val PERCENTAGE_DIVIDER = 100.0

    init {
        context?.theme?.obtainStyledAttributes(
            attrs, R.styleable.CircularProgressView, 0, 0
        )?.apply {
            this@CircularProgressView.stokeWidth = getInteger(R.styleable.CircularProgressView_stokeWidth, 40).toFloat()
            this@CircularProgressView.ovalSize = getInteger(R.styleable.CircularProgressView_ovalSize, 150).toFloat()
            this@CircularProgressView.parentArcColor = getColor(R.styleable.CircularProgressView_parentColor, Color.GRAY)
            this@CircularProgressView.fillArcColor = getColor(R.styleable.CircularProgressView_fillColor, Color.BLACK)
        }
    }

    private val parentArcPaint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
        color = getParentColor()
        strokeWidth = getStokeWidth()
    }

    fun getStokeWidth(): Float {
        return stokeWidth
    }

    fun setStokeWidth(stokeWidth: Int) {
        this@CircularProgressView.stokeWidth = stokeWidth.toFloat()
        invalidate()
        requestLayout()
    }

    fun getOvalSize(): Float {
        return ovalSize
    }

    fun setOvalSize(ovalSize: Int) {
        this@CircularProgressView.ovalSize = ovalSize.toFloat()
        invalidate()
        requestLayout()
    }


    fun getParentColor(): Int {
        return parentArcColor
    }

    fun setParentColor(stokeWidth: Int) {
        this@CircularProgressView.parentArcColor = stokeWidth
        invalidate()
        requestLayout()
    }

    fun getFillColor(): Int {
        return fillArcColor
    }

    fun setFillColor(fillColor: Int) {
        this@CircularProgressView.fillArcColor = fillColor
        invalidate()
        requestLayout()
    }

    private var currentPercentage = 0
    //...

    fun animateProgress(progressPercentage: Int) {
        // 1
        val progressValue = (if (progressPercentage < 0) 0 else if (progressPercentage > 100) 100 else progressPercentage).toFloat()
        val valuesHolder = PropertyValuesHolder.ofFloat(
            PERCENTAGE_VALUE_HOLDER,
            0f,
            progressValue
        )

        // 2
        val animator = ValueAnimator().apply {
            setValues(valuesHolder)
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()

            // 3
            addUpdateListener {
                // 4
                val percentage = it.getAnimatedValue(PERCENTAGE_VALUE_HOLDER) as Float

                // 5
                currentPercentage = percentage.toInt()

                // 6
                invalidate()
            }
        }
        // 7
        animator.start()
    }

    private fun getCurrentPercentageToFill() =
        (ARC_FULL_ROTATION_DEGREE * (currentPercentage / PERCENTAGE_DIVIDER)).toFloat()

    private val fillArcPaint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
        color = getFillColor()
        strokeWidth = getStokeWidth()
        // 1
        strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas?) {
        setSpace()
        canvas?.let {
            // 2
            drawBackgroundArc(it)
            // 3
            drawInnerArc(it)
        }
    }

    private fun drawBackgroundArc(it: Canvas) {
        it.drawArc(ovalSpace, 0f, 360f, false, parentArcPaint)
    }

    private fun drawInnerArc(canvas: Canvas) {
        // 4
        val percentageToFill = getCurrentPercentageToFill()
        canvas.drawArc(ovalSpace, 270f, percentageToFill, false, fillArcPaint)

    }

    private fun setSpace() {
        val horizontalCenter = (width.div(2)).toFloat()
        val verticalCenter = (height.div(2)).toFloat()
        val ovalSize = getOvalSize()
        ovalSpace.set(
            horizontalCenter - ovalSize,
            verticalCenter - ovalSize,
            horizontalCenter + ovalSize,
            verticalCenter + ovalSize
        )
    }

    companion object {
        // ...
        const val PERCENTAGE_VALUE_HOLDER = "percentage"
    }

}