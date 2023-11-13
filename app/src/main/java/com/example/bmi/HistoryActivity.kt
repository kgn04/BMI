package com.example.bmi

import android.content.Context
import android.content.SharedPreferences
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
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        deserialize()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdapter(dates, heights, weights, bmis, is_metrical_list)
    }

    private fun listFromSharedPreferences(key: String): List<String> {
        var list_as_str = sharedPref.getString(key, "")!!
        list_as_str = list_as_str.substring(0, list_as_str.length - 1)
        return list_as_str.split(",").map { it.trim() }
    }

    private fun deserialize() {
        sharedPref = getSharedPreferences("historyData", Context.MODE_PRIVATE)
        dates = listFromSharedPreferences("DATES")
        heights = listFromSharedPreferences("HEIGHTS")
        weights = listFromSharedPreferences("WEIGHTS")
        bmis = listFromSharedPreferences("BMIS")
        is_metrical_list = listFromSharedPreferences("IS_METRICAL_LIST")
    }
}