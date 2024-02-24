package com.example.myAppAPI24

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var myView: MyView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 點選鴨鴨程式碼
        myView = MyView(this);
        setContentView(myView)
    }

    override fun onPause() {
        Log.d("state change","暫停")
        super.onPause()
        myView.gotBackgrounded()
    }

    override fun onResume() {
        Log.d("state change","繼續")
        super.onResume()
        myView.getForegrounded()
    }

    override fun onDestroy() {
        super.onDestroy()
        myView.releaseResources() // 釋放資源
    }
}