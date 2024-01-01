package com.example.myAppAPI24

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.random.Random

class MyView(c: Context) : View(c), TickListener {
    private val paint1: Paint = Paint()
    private val paint2: Paint = Paint()
    private var screenWidth: Float = 0f
    private var screenHeight: Float = 0f
    private var rubberDuckList = ArrayList<RubberDuck>()
    private var firstTimeDraw = true
    private val timer: Timer = Timer(Looper.getMainLooper())
    private var size = 0

    init {
        paint1.color = Color.RED;
        paint2.color = Color.BLUE; // 顏色
        paint2.strokeWidth = 10f; // 粗度
        paint2.style = Paint.Style.STROKE // 不要填滿
    }

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
        for (d in rubberDuckList) {
            d.drawRubberDuck(canvas)
        }
    }

    /**
     * 產生鴨鴨ㄋ
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
//                    rubberDuckList.remove(d)
//                    break // 法一：直接結束迴圈防止ConcurrentModificationException

//                    Toast.makeText(context, "你按了一隻鴨鴨~", Toast.LENGTH_SHORT).show()
//                    d.pressed() // 繪製黑白鴨
//                    invalidate()
                }
            }
            rubberDuckList.removeAll(tappedRubberDucks) // 結束迴圈後在一起刪除
            invalidate()

            /**
             * DialogInterface.OnClickListener 是 functional interface
             * 只有一個 function 需要實作
             */
            class YesButtonListener : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    makeRubberDucks() // 產生鴨鴨
                }
            }

            class NoButtonListener : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    (context as Activity).finish() // 直接結束APP
                }
            }

            if (rubberDuckList.size == 0) {
                val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
                alertDialogBuilder.setTitle("遊戲結束")
                alertDialogBuilder.setMessage("所有的鴨鴨都被消滅了!需要更多鴨鴨嗎?")
                alertDialogBuilder.setCancelable(false)
                alertDialogBuilder.setPositiveButton("好", YesButtonListener())
                alertDialogBuilder.setNegativeButton("不用了", NoButtonListener())
                alertDialogBuilder.create().show()
            }
        }
//        else if (event.action == MotionEvent.ACTION_UP) {
//            for (d in rubberDuckList) { // 將鴨子裝入list中
//                if (d.contains(event.x, event.y)) { //用 event.x event.y 輸入鴨子 鴨子判斷是否被選中
//                    Toast.makeText(context, "你放開了鴨鴨~", Toast.LENGTH_SHORT).show()
//                    d.unpressed()
//                    invalidate()
//                }
//            }
//        }
        return true
    }

    override fun tick() {
        this.invalidate()
    }
}