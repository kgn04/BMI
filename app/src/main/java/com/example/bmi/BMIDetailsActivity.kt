package com.example.bmi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.Locale

class BMIDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmidetails)
        val sharedPref = getSharedPreferences("BMIPrefs", Context.MODE_PRIVATE)
        val bmi = sharedPref.getFloat("BMI", 0.0F)
        val bmiTextView: TextView = findViewById(R.id.bmiTextView2)
        bmiTextView.text = "BMI: ${"%,.2f".format(Locale.ENGLISH, bmi)}"
        changeDescription(bmi)
    }

    private fun changeDescription(bmi: Float) {
        val typeTextView: TextView = findViewById(R.id.typeTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        if (bmi < 18.5) {
            typeTextView.text = getString(R.string.niedowaga)
            descriptionTextView.text = getString(R.string.niedowaga_opis)
        } else if (bmi < 25.0) {
            typeTextView.text = getString(R.string.optimum)
            descriptionTextView.text = getString(R.string.optimum_opis)
        } else if (bmi < 30.0) {
            typeTextView.text = getString(R.string.nadwaga)
            descriptionTextView.text = getString(R.string.nadwaga_opis)
        } else {
            typeTextView.text = getString(R.string.otylosc)
            descriptionTextView.text = getString(R.string.otylosc_opis)
        }
    }
}