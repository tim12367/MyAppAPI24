package com.example.myAppAPI24

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.RectF
import com.example.myappapi24.R


class RubberDuck(res: Resources, x: Float, y: Float, size: Int) : TickListener, Comparable<RubberDuck> {
    private val bounds: RectF = RectF(x, y, x + size, y + size)
    private var duckImage: Bitmap = BitmapFactory.decodeResource(res, R.drawable.duck)
    private var grayDuckImage: Bitmap = BitmapFactory.decodeResource(res, R.drawable.gray_duck)

    init {
        duckImage = Bitmap.createScaledBitmap(duckImage, size, size, true)
        grayDuckImage = Bitmap.createScaledBitmap(grayDuckImage, size, size, true)
    }

    /**
     * 隨機移動位置
     */
    fun dance() {
        val dx = (Math.random() * 10 - 5).toFloat()
        val dy = (Math.random() * 10 - 5).toFloat()
        bounds.offset(dx, dy)
    }

    fun drawRubberDuck(c: Canvas) {
        c.drawBitmap(duckImage, bounds.left, bounds.top, null)
    }

    fun contains(x: Float, y: Float): Boolean {
        return bounds.contains(x, y)
    }

    override fun tick() {
        this.dance()
    }

    override fun compareTo(other: RubberDuck): Int {
        return (other.bounds.top - this.bounds.top).toInt()
    }
}