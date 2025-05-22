package com.example.healthy_habits_secondapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val goalInput = findViewById<EditText>(R.id.goalInput)
        val themeSwitch = findViewById<Switch>(R.id.themeSwitch)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val backButton = findViewById<Button>(R.id.backButton)

        val prefs = getSharedPreferences("HabitPrefs", Context.MODE_PRIVATE)
        val savedGoal = prefs.getString("dailyGoal", "")
        val isDarkMode = prefs.getBoolean("darkMode", false)

        Toast.makeText(this, "Preferences Saved!", Toast.LENGTH_SHORT).show()

        saveButton.setOnClickListener {
            val newGoal = goalInput.text.toString()
            val darkModeEnabled = themeSwitch.isChecked

            prefs.edit().apply {
                putString("dailyGoal", newGoal)
                putBoolean("darkMode", darkModeEnabled)
                apply()
            }

            AppCompatDelegate.setDefaultNightMode(
                if (darkModeEnabled) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )

            Toast.makeText(this, "Preferences Saved!", Toast.LENGTH_SHORT).show()
        }


        goalInput.setText(savedGoal)
        themeSwitch.isChecked = isDarkMode

        // Set theme on launch
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        saveButton.setOnClickListener {
            val newGoal = goalInput.text.toString()
            val darkModeEnabled = themeSwitch.isChecked

            prefs.edit().apply {
                putString("dailyGoal", newGoal)
                putBoolean("darkMode", darkModeEnabled)
                apply()
            }

            AppCompatDelegate.setDefaultNightMode(
                if (darkModeEnabled) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        // Back button to MainActivity
        backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
