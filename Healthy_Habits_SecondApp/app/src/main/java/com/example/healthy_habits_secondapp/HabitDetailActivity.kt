package com.example.healthy_habits_secondapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HabitDetailActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_detail)

        prefs = getSharedPreferences("HabitPrefs", MODE_PRIVATE)

        val habit = intent.getStringExtra("habitName") ?: "Unknown Habit"
        prefs.edit().putString("lastSelectedHabit", habit).apply()

        val titleTextView = findViewById<TextView>(R.id.habitTitleTextView)
        val detailTextView = findViewById<TextView>(R.id.habitDetailTextView)
        val imageView = findViewById<ImageView>(R.id.habitImageView)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Habit Details"

        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish() // closes HabitDetailActivity and returns to MainActivity
        }


        titleTextView.text = habit

        when (habit) {
            "Meditate" -> {
                imageView.setImageResource(R.drawable.meditate)
                detailTextView.text = "Meditation helps reduce stress, improve focus, and boost emotional well-being. Even 10 minutes a day can make a difference."
            }
            "Hydrate" -> {
                imageView.setImageResource(R.drawable.hydrate)
                detailTextView.text = "Staying hydrated improves energy levels, skin health, and body function. Aim for 6–8 glasses of water daily!"
            }
            "Walk" -> {
                imageView.setImageResource(R.drawable.walk)
                detailTextView.text = "Walking daily can boost cardiovascular health, burn calories, and improve mood. Try to get in 30 minutes a day."
            }
            else -> {
                imageView.setImageResource(R.drawable.ic_launcher_foreground)
                detailTextView.text = "No details found for this habit."
            }
        }
    }
}
