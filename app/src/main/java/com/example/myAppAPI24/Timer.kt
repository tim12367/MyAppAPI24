package com.example.myAppAPI24

import android.os.Handler
import android.os.Looper
import android.os.Message

class Timer(looper: Looper) : Handler(looper) {
    private val listeners: ArrayList<TickListener> = ArrayList()
    private var paused = false

    init {
        this.sendMessageDelayed(Message.obtain(), 0)
    }

    fun register(t: TickListener) {
        listeners.add(t)
    }

    fun unregister(t: TickListener) {
        listeners.remove(t)
    }

    fun notifyListener() {
        for (listener in listeners) {
            listener.tick()
        }
    }

    /**
     * 暫停timer
     */
    fun pause() {
        paused = true
    }

    /**
     * 開始timer
     */
    fun unPause() {
        paused = false
    }

    override fun handleMessage(msg: Message) {
        if (!paused) {
            notifyListener()
        }
        sendMessageDelayed(Message.obtain(), 100)
    }
}