package com.example.healthy_habits_secondapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra("username")
        Toast.makeText(this, "Welcome, $username!", Toast.LENGTH_SHORT).show()

        findViewById<Button>(R.id.progressButton).setOnClickListener {
            startActivity(Intent(this, ProgressActivity::class.java))
        }

        findViewById<Button>(R.id.settingsButton).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        findViewById<Button>(R.id.meditateButton).setOnClickListener {
            Toast.makeText(this, "Meditation habit selected!", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.hydrateButton).setOnClickListener {
            Toast.makeText(this, "Hydration habit selected!", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.walkButton).setOnClickListener {
            Toast.makeText(this, "Walking habit selected!", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.instructionButton).setOnClickListener {
            val intent = Intent(this, InstructionActivity::class.java)
            startActivity(intent)
        }

        fun openHabitDetail(habitName: String) {
            val intent = Intent(this, HabitDetailActivity::class.java)
            intent.putExtra("habitName", habitName)
            startActivity(intent)
        }

        findViewById<Button>(R.id.meditateButton).setOnClickListener {
            openHabitDetail("Meditate")
        }

        findViewById<Button>(R.id.hydrateButton).setOnClickListener {
            openHabitDetail("Hydrate")
        }

        findViewById<Button>(R.id.walkButton).setOnClickListener {
            openHabitDetail("Walk")
        }

        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().clear().apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
