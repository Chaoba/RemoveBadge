package com.mushuichuan.removebadge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.samsung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.sendToSamsumg(MainActivity.this, getPackageName(), MainActivity.this.getComponentName().getClassName(),3);
            }
        });

    }

}
