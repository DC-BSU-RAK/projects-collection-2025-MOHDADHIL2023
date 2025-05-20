package com.example.nancalculatorlocationapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var citySpinner: Spinner
    lateinit var placeTypeSpinner: Spinner
    lateinit var showButton: Button
    lateinit var resultText: TextView
    lateinit var instructionButton: Button  // Added reference for the new button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        citySpinner = findViewById(R.id.citySpinner)
        placeTypeSpinner = findViewById(R.id.placeTypeSpinner)
        showButton = findViewById(R.id.showButton)
        resultText = findViewById(R.id.resultText)
        instructionButton = findViewById(R.id.instructionButton)

        val cities = arrayOf("RAK", "Dubai", "Paris", "Tokyo", "New York", "London", "Rome", "Delhi", "Sydney")
        val types = arrayOf("Restaurant", "Park", "Museum", "Mall", "Hospital", "Theatre", "Hotel", "Beach")

        val cityAdapter = ArrayAdapter(this, R.layout.spinner_item_white, cities)
        cityAdapter.setDropDownViewResource(R.layout.spinner_item_white)
        citySpinner.adapter = cityAdapter

        val typeAdapter = ArrayAdapter(this, R.layout.spinner_item_white, types)
        typeAdapter.setDropDownViewResource(R.layout.spinner_item_white)
        placeTypeSpinner.adapter = typeAdapter

        val locationMap = mapOf(
            Pair("Paris", "Museum") to "Visit the Louvre Museum in Paris.",
            Pair("Dubai", "Mall") to "Explore the Dubai Mall.",
            Pair("Dubai", "Beach") to "Explore the Jumeriah Beach, Kite Beach.",
            Pair("Dubai", "Park") to "Explore the Dubai miracle Garden.",
            Pair("RAK", "Mall") to "Explore the Manar Mall.",
            Pair("RAK", "Beach") to "Explore the Flamingo Beach.",
            Pair("RAK", "Park") to "Explore the Saqr Park.",
            Pair("Tokyo", "Park") to "Relax at Ueno Park in Tokyo.",
            Pair("New York", "Hotel") to "Check in at The Plaza Hotel.",
            Pair("London", "Theatre") to "Watch a show at the Globe Theatre.",
            Pair("Rome", "Restaurant") to "Dine at a Trattoria in Rome.",
            Pair("Delhi", "Hospital") to "Visit AIIMS Hospital in Delhi.",
            Pair("Sydney", "Beach") to "Bondi Beach is a great spot in Sydney."
        )

        showButton.setOnClickListener {
            val selectedCity = citySpinner.selectedItem.toString()
            val selectedType = placeTypeSpinner.selectedItem.toString()

            // Show quick Toast splash
            Toast.makeText(
                this,
                "Selected: $selectedCity - $selectedType",
                Toast.LENGTH_SHORT
            ).show()

            val key = Pair(selectedCity, selectedType)
            val message = locationMap[key] ?: "No famous $selectedType found in $selectedCity."

            // Show formatted result
            resultText.text = "City: $selectedCity\nPlace: $selectedType\n\n$message"
        }

        instructionButton.setOnClickListener {
            val intent = Intent(this, InstructionActivity::class.java)
            startActivity(intent)
        }
    }
}
