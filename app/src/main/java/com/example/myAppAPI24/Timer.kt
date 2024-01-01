package com.example.myAppAPI24

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

class Timer(looper: Looper) : Handler(looper) {
    private val listeners: ArrayList<TickListener> = ArrayList()

    init {
        this.sendMessageDelayed(Message.obtain(), 0)
    }

    fun register(t: TickListener) {
        listeners.add(t)
    }

    fun unregister(t: TickListener) {
        listeners.remove(t)
    }

    fun notifyListener () {
        for (listener in listeners) {
            listener.tick()
        }
    }

    override fun handleMessage(msg: Message) {
        // some method goes here...
        Log.d("Timer", "刷新!")
        notifyListener()
        sendMessageDelayed(Message.obtain(), 100)
    }
}