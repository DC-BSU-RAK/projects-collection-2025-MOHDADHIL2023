package com.example.nancalculatorlocationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InstructionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction_splash)

        // Initialize the continue button
        val continueButton = findViewById<Button>(R.id.continueButton)

        // Navigate to MainActivity when button is clicked
        continueButton?.setOnClickListener {
            val intent = Intent(this@InstructionActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish current activity so user can't return to it with back button
        }
    }
}
