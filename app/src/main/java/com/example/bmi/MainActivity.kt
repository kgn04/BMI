package com.example.bmi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var bmiTextView: TextView
    private var metrical = true

    private var dates = ""
    private var heights = ""
    private var weights = ""
    private var bmis = ""
    private var is_metrical_list = ""

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        bmiTextView = findViewById(R.id.bmiTextView)
        weightEditText = findViewById(R.id.editTextNumber)
        heightEditText = findViewById(R.id.editTextNumber2)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
            updateBMI()
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            dates += "${(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))},"
            heights += "${(heightEditText.text.toString())},"
            weights += "${(weightEditText.text.toString())},"
            bmis += "${(calculateBMI(weightEditText.text.toString().toDouble(),
                heightEditText.text.toString().toDouble()).toString())},"
            is_metrical_list += "${(metrical.toString())},"
        }
        bmiTextView.setOnClickListener {
            val intent = Intent(this, BMIDetailsActivity::class.java)
            val sharedPref = getSharedPreferences("BMIPrefs", Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putFloat("BMI", calculateBMI(weightEditText.text.toString().toDouble(),
                    heightEditText.text.toString().toDouble()).toFloat())
                apply()
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.history -> {
                val intent = Intent(this, HistoryActivity::class.java)
                val sharedPref = getSharedPreferences("historyData", Context.MODE_PRIVATE)
                Toast.makeText(this, dates, Toast.LENGTH_SHORT).show()
                with (sharedPref.edit()) {
                    putString("DATES", dates.substring(0, dates.length - 1))
                    putString("HEIGHTS", heights.substring(0, heights.length - 1))
                    putString("WEIGHTS", weights.substring(0, weights.length - 1))
                    putString("BMIS", bmis.substring(0, bmis.length - 1))
                    putString("IS_METRICAL_LIST", is_metrical_list.substring(0, is_metrical_list.length - 1))
                    apply()
                }
                startActivity(intent)
                true
            }
            R.id.units -> {
                changeUnits()
                true
            }
            R.id.about_author -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun calculateBMI(weight: Double, height: Double): Double {
        if (metrical)
            return weight / ((height / 100) * (height / 100))
        return weight / ((height * height * 730))
    }

    private fun updateBMI() {
        val weightStr = weightEditText.text.toString()
        val heightStr = heightEditText.text.toString()

        if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
            val weight = weightStr.toDouble()
            val height = heightStr.toDouble()

            val bmi = "%,.2f".format(Locale.ENGLISH, calculateBMI(weight, height))
            bmiTextView.text = "BMI: $bmi"
        }
    }

    private fun changeUnits() {
        val heightTextView: TextView = findViewById(R.id.heightTextView)
        val weightTextView: TextView = findViewById(R.id.weightTextView)
        if (heightTextView.text.toString() == getString(R.string.wzrost_cm)) {
            heightTextView.text = getString(R.string.wzrost_in)
            weightTextView.text = getString(R.string.waga_lb)
        } else {
            heightTextView.text = getString(R.string.wzrost_cm)
            weightTextView.text = getString(R.string.waga_kg)
        }
        metrical = !metrical
        updateBMI()
    }
}