package com.ilerna.pac_pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Activity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        // Activity1 back button
        val btnBack = findViewById<Button>(R.id.btnBacktoA1)
        btnBack.setOnClickListener {
            finish()
        }
    }
}