package com.example.myAppAPI24

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 點選鴨鴨程式碼
        var myView = MyView(this);
        setContentView(myView)
    }
}