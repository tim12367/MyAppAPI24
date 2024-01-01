package com.example.myAppAPI24

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.RectF
import android.util.Log
import com.example.myappapi24.R


class RubberDuck(res: Resources, x: Float, y: Float, size: Int): TickListener {
    private val bounds: RectF = RectF(x, y, x + size, y + size)
    private var duckImage: Bitmap = BitmapFactory.decodeResource(res, R.drawable.duck)
    private var grayDuckImage: Bitmap = BitmapFactory.decodeResource(res, R.drawable.gray_duck)
    private var pressed = false;

    init {
        duckImage = Bitmap.createScaledBitmap(duckImage, size, size, true)
        grayDuckImage = Bitmap.createScaledBitmap(grayDuckImage, size, size, true)
    }

    fun pressed() {
        pressed = true
    }

    fun unpressed() {
        pressed = false
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
        if (pressed) {
            c.drawBitmap(grayDuckImage, bounds.left, bounds.top, null)
        } else {
            c.drawBitmap(duckImage, bounds.left, bounds.top, null)
        }
    }

    fun contains(x: Float, y: Float): Boolean {
        return bounds.contains(x, y)
    }

    override fun tick() {
        this.dance()
    }
}