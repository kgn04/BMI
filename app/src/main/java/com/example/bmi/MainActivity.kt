package com.example.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var bmiTextView: TextView


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
        }
        bmiTextView.setOnClickListener {
            val intent = Intent(this, BMIDetailsActivity::class.java)
            intent.putExtra("BMI_VAL",
                calculateBMI(weightEditText.text.toString().toDouble(),
                heightEditText.text.toString().toDouble()))
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.history -> {
                val intent = Intent(this, HistoryActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.units_switch -> {
                //Change bool value
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
        return weight / ((height / 100) * (height / 100))
    }

    private fun updateBMI() {
        val weightStr = weightEditText.text.toString()
        val heightStr = heightEditText.text.toString()

        if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
            val weight = weightStr.toDouble()
            val height = heightStr.toDouble()

            val bmi = calculateBMI(weight, height)
            bmiTextView.text = "BMI: $bmi"
        } else {
            // Handle the case when either weight or height is not provided
            bmiTextView.text = "BMI: N/A"
        }
    }
}