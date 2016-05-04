package com.mushuichuan.removebadge

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        var num=1;
        findViewById(R.id.samsung)!!.setOnClickListener { Util.sendToOneApp(this@MainActivity, packageName, this@MainActivity.componentName.className, num++) }
        findViewById(R.id.samsung_add_all)!!.setOnClickListener { Util.sendToAll(this@MainActivity, 100) }
    }

}
