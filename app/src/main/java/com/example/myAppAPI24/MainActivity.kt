package com.example.myAppAPI24

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // layout
        val linearLayout = LinearLayout(this)
        // button
        val button = Button(this)
        button.text = "前往第二個Activity"
        button.setOnClickListener {
            val intent = Intent() //implicit intent
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "你好嗎")
            intent.type = "text/plain"
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
//            val intent = Intent(this, SecondActivity::class.java)
//            startActivity(intent)
        }
        // text
        val textView = TextView(this)
        textView.text = "目前在第一個Activity"

        linearLayout.addView(button)
        linearLayout.addView(textView)
        setContentView(linearLayout)
    }
}