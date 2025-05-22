package com.example.healthy_habits_secondapp

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        val sharedPref = getSharedPreferences("HealthyHabitsPrefs", Context.MODE_PRIVATE)
        val selectedHabit = sharedPref.getString("selected_habit", "No habit selected")
        val currentProgress = sharedPref.getInt("habit_progress", 3)
        val dailyGoal = sharedPref.getString("dailyGoal", "5")?.toIntOrNull() ?: 5

        val habitTextView = findViewById<TextView>(R.id.habitTextView)
        val progressTextView = findViewById<TextView>(R.id.progressTextView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val goalTextView = findViewById<TextView>(R.id.goalTextView)
        val routineTextView = findViewById<TextView>(R.id.routineTextView)
        val feedbackTextView = findViewById<TextView>(R.id.feedbackTextView)

        habitTextView.text = "Selected Habit: $selectedHabit"
        progressTextView.text = "You've completed $currentProgress day(s)"
        goalTextView.text = "Your goal: $dailyGoal day(s)"

        // Progress bar setup
        progressBar.max = dailyGoal
        progressBar.progress = currentProgress

        // Routine suggestion
        routineTextView.text = when (selectedHabit) {
            "Meditate" -> "Suggested Routine:\n- 5 mins Morning\n- 10 mins Evening"
            "Hydrate" -> "Suggested Routine:\n- Drink 1 glass/hour\n- Track in the app"
            "Walk" -> "Suggested Routine:\n- 15 min walk after lunch\n- 20 min before dinner"
            else -> "No routine found for this habit"
        }

        // Feedback based on progress
        feedbackTextView.text = when {
            currentProgress >= dailyGoal -> "🎉 Excellent! You've reached your goal!"
            currentProgress >= dailyGoal / 2 -> "💪 Keep going! You're more than halfway there!"
            else -> "🚀 Let's boost your consistency today!"
        }

        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}
