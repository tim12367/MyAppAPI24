package com.example.myAppAPI24

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import java.util.Collections
import kotlin.random.Random

class MyView(c: Context) : View(c), TickListener {
    private var screenWidth: Float = 0f
    private var screenHeight: Float = 0f
    private var rubberDuckList = ArrayList<RubberDuck>()
    private var firstTimeDraw = true
    private val timer: Timer = Timer(Looper.getMainLooper())
    private var size = 0

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        if (firstTimeDraw) {
            screenWidth = width.toFloat()
            screenHeight = height.toFloat()
            size = (screenWidth * 0.2f).toInt()

            makeRubberDucks()

            timer.register(this)

            firstTimeDraw = false
        }

        Collections.sort(rubberDuckList) // 排序鴨子

        for (d in rubberDuckList) {
            d.drawRubberDuck(canvas)
        }
    }

    /**
     * 產生鴨鴨
     */
    private fun makeRubberDucks() {
        for (i in 1..10) {
            val rx = Random.nextInt(10, width - size).toFloat()
            val ry = Random.nextInt(10, height - size).toFloat()
            val rubberDuck = RubberDuck(resources, rx, ry, size)
            rubberDuckList.add(rubberDuck)
            timer.register(rubberDuck)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val tappedRubberDucks: ArrayList<RubberDuck> = ArrayList()

            for (d in rubberDuckList.reversed()) { // 將鴨子裝入list中，反轉後讓前方鴨子先被刪除
                if (d.contains(event.x, event.y)) { //用 event.x event.y 輸入鴨子 鴨子判斷是否被選中
                    tappedRubberDucks.add(d) // 法二：先在刪除列表內，加入需要刪除的鴨子
                    timer.unregister(d) // Subject 註銷
                }
            }
            rubberDuckList.removeAll(tappedRubberDucks) // 結束迴圈後在一起刪除
            invalidate()

            if (rubberDuckList.size == 0) {
                val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
                alertDialogBuilder.setTitle("遊戲結束")
                alertDialogBuilder.setMessage("所有的鴨鴨都被消滅了!需要更多鴨鴨嗎?")
                alertDialogBuilder.setCancelable(false)
                alertDialogBuilder.setPositiveButton("好") { _, _ -> makeRubberDucks() }// 產生鴨鴨
                alertDialogBuilder.setNegativeButton("不用了") { _, _ -> (context as Activity).finish() }// 直接結束APP
                alertDialogBuilder.create().show() // 顯示alertDialog
            }
        }
        return true
    }

    override fun tick() {
        this.invalidate()
    }
}
