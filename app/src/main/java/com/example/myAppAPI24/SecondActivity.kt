package com.example.myAppAPI24

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView

class SecondActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val string = intent.getStringExtra(Intent.EXTRA_TEXT)

        val tv = TextView(this)
        tv.text = string
        this.setContentView(tv)
    }
}