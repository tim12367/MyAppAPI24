package com.example.myAppAPI24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.myappapi24.R
import java.util.concurrent.CountDownLatch
import java.util.concurrent.locks.ReentrantLock

/**
 *
 */
class MainActivity : AppCompatActivity() {
//    lateinit var textView: TextView

//    var counter = 0
//    val handler = Handler(Looper.getMainLooper())
//    val lock = ReentrantLock()

//    companion object {
//        val contDown = CountDownLatch(1)
//    }
    /**
     * counter +1
     * 更新UI
     */
//    inner class AddOne: Runnable {
//        override fun run() {
//            // COUNTER ++
//            lock.lock()
//            try {
//                counter ++
//            } finally {
//                lock.unlock() // 確保鎖被釋放
//            }
//            handler.post{
//                // main thread 更新UI
//                textView.text = counter.toString()
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 點選鴨鴨程式碼
        var myView = MyView(this);
        setContentView(myView)

//        setContentView(R.layout.activity_main)

//        val button: Button = findViewById(R.id.button)
//        textView = findViewById(R.id.text1)
//        val otherThread = OtherThread()
//        otherThread.start()
//
//        try {
//            // 在other thread handler 完成前都必須等待
//            contDown.await()
//        } catch (e :InterruptedException) {
//            e.printStackTrace()
//        }
        // 用匿名Class
//        button.setOnClickListener(View.OnClickListener {
//            Thread(AddOne()).start()
//            println("執行 setOnClickListener 的thread 是${Thread.currentThread().name}")
//            otherThread.myhandler.sendMessage(Message.obtain())
//            otherThread.myhandler.updateUI(textView)
//        })
//        for(i in 1..5000){
//            button.performClick()
//        }
    }
}