package com.example.newquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ScienceButton = findViewById<Button>(R.id.scienceButton)
        
        ScienceButton.setOnClickListener { 
            val intent=Intent(this,ScienceQuiz :: class.java)
            startActivity(intent)
        }
    }
}