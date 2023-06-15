package com.example.twowayprogressgraph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar: DualOvalProgressBar = findViewById(R.id.progressBar)
        progressBar.setMaxProgress(100f)
        progressBar.setProgress1(30f)
        progressBar.setProgress2(70f)

    }
}