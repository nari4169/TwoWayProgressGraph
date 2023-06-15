package com.example.twowayprogressgraph

import android.content.Context
import android.graphics.Canvas
import android.graphics.ComposePathEffect
import android.graphics.CornerPathEffect
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.twowayprogressgraph.common.DisplayExt.toDp
import com.example.twowayprogressgraph.common.DisplayExt.toPx
import com.example.twowayprogressgraph.util.Logger

/**
 * progress header 16x16
 * 양방향 or 단방향 으로 진행.
 * 단방향인 경우 진행&완료(체크)이미지가 Rect 우측 하단 끝에 노출됨.
 * 진행률 100% 경우, 체크 이미지 변경
 */
class RoundedRectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val roundCorner = 141.toPx().toFloat()

    // 전체 progress line
    private val backgroundPaint = Paint().apply {
        color = context.getColor(R.color.bg_progress)
        strokeWidth = 8.toPx().toFloat()
        isAntiAlias = true
        style = Paint.Style.STROKE
        pathEffect = CornerPathEffect(roundCorner)
        strokeCap = Paint.Cap.ROUND
    }

    // 진행률 만큼 회색 라인을 따라가는 progress bar
    private val firstPaint = Paint().apply {
        color = context.getColor(R.color.primary)
        strokeWidth = 8.toPx().toFloat()
        isAntiAlias = true
        style = Paint.Style.STROKE
        pathEffect = CornerPathEffect(roundCorner)
        strokeCap = Paint.Cap.ROUND
//        strokeJoin = Paint.Join.ROUND
    }

    private var backGroundPath = Path()
    private var firstPath = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Logger.e("w: ${w.toDp()} / h: ${h.toDp()} / ")

        val rect = RectF(0f, 0f, w.toFloat(), h.toFloat())

        backGroundPath = Path().apply {
            addRoundRect(rect, roundCorner, roundCorner, Path.Direction.CW)
        }

        firstPath = Path().apply {
            addRoundRect(rect, roundCorner, roundCorner, Path.Direction.CW)
        }

//        myPath = Path().apply {
//            moveTo(w.toFloat(), h.toFloat())
//            lineTo(w.toFloat(), 0f)
//        }
//        myPath.close()

        val pathMeasure = PathMeasure(firstPath, false)
        val length = pathMeasure.length // 그래프 전체 길이
        val firstProgress = length - length * 0.5f  // 전체 길이의 50%

        val pathEffect = DashPathEffect(floatArrayOf(length, length), firstProgress)
        val cornerPathEffect = CornerPathEffect(roundCorner)
//        firstPaint.pathEffect = ComposePathEffect(cornerPathEffect, pathEffect)
        firstPaint.pathEffect = pathEffect
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.drawRoundRect()
        canvas.drawPath(backGroundPath, backgroundPaint)
        canvas.drawPath(firstPath, firstPaint)
    }
}