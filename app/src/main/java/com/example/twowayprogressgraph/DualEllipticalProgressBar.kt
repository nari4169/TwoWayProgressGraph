package com.example.twowayprogressgraph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class DualOvalProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val progressPaint1: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val progressPaint2: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val progressPath1: Path = Path()
    private val progressPath2: Path = Path()

    private var progress1: Float = 0f
    private var progress2: Float = 0f
    private var maxProgress: Float = 100f

    init {
        progressPaint1.color = Color.BLUE
        progressPaint2.color = Color.RED
        progressPaint1.style = Paint.Style.STROKE
        progressPaint2.style = Paint.Style.STROKE
        progressPaint1.strokeWidth = 40f
        progressPaint2.strokeWidth = 40f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val horizontalRadius = Math.min(width, height) / 2f - progressPaint1.strokeWidth / 2f
        val verticalRadius = horizontalRadius / 2f

        // Calculate the sweep angles for the progress arcs
        val progressSweepAngle1 = 360f * progress1 / maxProgress
        val progressSweepAngle2 = 360f * progress2 / maxProgress

        // Calculate the start and end angles for the progress arcs
        val startAngle1 = -90f
        val endAngle1 = startAngle1 + progressSweepAngle1
        val startAngle2 = endAngle1
        val endAngle2 = startAngle2 + progressSweepAngle2

        // Create the progress paths
        progressPath1.reset()
        progressPath1.addArc(
            centerX - horizontalRadius,
            centerY - verticalRadius,
            centerX + horizontalRadius,
            centerY + verticalRadius,
            startAngle1,
            progressSweepAngle1
        )
        progressPath2.reset()
        progressPath2.addArc(
            centerX - horizontalRadius,
            centerY - verticalRadius,
            centerX + horizontalRadius,
            centerY + verticalRadius,
            startAngle2,
            progressSweepAngle2
        )

        // Draw the progress paths
        canvas.drawPath(progressPath1, progressPaint1)
        canvas.drawPath(progressPath2, progressPaint2)
    }

    fun setProgress1(progress: Float) {
        this.progress1 = progress
        invalidate()
    }

    fun setProgress2(progress: Float) {
        this.progress2 = progress
        invalidate()
    }

    fun setMaxProgress(maxProgress: Float) {
        this.maxProgress = maxProgress
        invalidate()
    }
}

