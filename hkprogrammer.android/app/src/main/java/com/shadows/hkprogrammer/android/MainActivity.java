package com.shadows.hkprogrammer.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shadows.hkprogrammer.android.managers.ActivityManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager.OpenSelectProviderActivity(this);
    }
}
