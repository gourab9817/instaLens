package com.example.instalens.presentation.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.instalens.domain.model.Detection
import kotlin.random.Random

class DetectionBoxView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var detection: Detection? = null
    private val paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 8f
    }

    fun setDetection(detection: Detection) {
        this.detection = detection
        paint.color = getColorForLabel(detection.detectedObjectName)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        detection?.let {
            val screenWidth = width.toFloat()
            val screenHeight = height.toFloat()

            val xScale = screenWidth / it.tensorImageWidth
            val yScale = screenHeight / it.tensorImageHeight

            val scaledBox = RectF(
                it.boundingBox.left * xScale,
                it.boundingBox.top * yScale,
                it.boundingBox.right * xScale,
                it.boundingBox.bottom * yScale
            ).also { box ->
                box.left = box.left.coerceAtLeast(0f)
                box.top = box.top.coerceAtLeast(0f)
                box.right = box.right.coerceAtMost(screenWidth)
                box.bottom = box.bottom.coerceAtMost(screenHeight)
            }

            canvas.drawRect(scaledBox, paint)

            val text = "${it.detectedObjectName} ${(it.confidenceScore * 100).toInt()}%"
            paint.textSize = 20 * resources.displayMetrics.density
            canvas.drawText(text, scaledBox.left, scaledBox.top - 10, paint)
        }
    }

    private val labelColorMap = mutableMapOf<String, Int>()

    private fun getColorForLabel(label: String): Int {
        return labelColorMap.getOrPut(label) {
            Random.nextInt()
        }
    }
}