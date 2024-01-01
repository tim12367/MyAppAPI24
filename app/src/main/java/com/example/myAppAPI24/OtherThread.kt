//package com.example.myAppAPI24
//
//import android.os.Handler
//import android.os.Looper
//import android.os.Message
//import android.view.View
//import android.widget.TextView
//
//class Myhandler(looper: Looper) : Handler(looper) {
//    var counter = 0
//
//    override fun handleMessage(msg: Message) {
//        println("執行HandleMessage 的thread 是${Thread.currentThread().name}")
//        counter++
//    }
//
//    fun updateUI(view: TextView) {
//        view.post {
//            view.text = counter.toString()
//        }
//    }
//}
//
//class OtherThread : Thread() {
//    lateinit var myhandler: Myhandler
//
//    override fun run() {
//
//        Looper.prepare()
//
//        // init a handler
//        myhandler = Myhandler(Looper.myLooper()!!)
//        MainActivity.contDown.countDown()
//
//        Looper.loop()
//    }
//}