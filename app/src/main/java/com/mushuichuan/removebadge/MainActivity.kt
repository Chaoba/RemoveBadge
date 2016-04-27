package com.mushuichuan.removebadge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        findViewById(R.id.samsung)!!.setOnClickListener { Util.sendToSamSumg(this@MainActivity, packageName, this@MainActivity.componentName.className, 3) }

    }

}
