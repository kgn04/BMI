package com.example.bmi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {

    private lateinit var dates: List<String>
    private lateinit var heights: List<String>
    private lateinit var weights: List<String>
    private lateinit var bmis: List<String>
    private lateinit var is_metrical_list: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getSharedPreferences("historyData", Context.MODE_PRIVATE)
        dates = sharedPref.getString("DATES", "")!!.split(",").map { it.trim() }
        heights = sharedPref.getString("HEIGHTS", "")!!.split(",").map { it.trim() }
        weights = sharedPref.getString("WEIGHTS", "")!!.split(",").map { it.trim() }
        bmis = sharedPref.getString("BMIS", "")!!.split(",").map { it.trim() }
        is_metrical_list = sharedPref.getString("IS_METRICAL_LIST", "")!!.split(",").map { it.trim() }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdapter(dates, heights, weights, bmis, is_metrical_list)
    }
}