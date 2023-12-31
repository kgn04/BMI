package com.example.bmi

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale
import android.graphics.Color


class CustomAdapter(private val dates: List<String>, private val heights: List<String>,
                    private val weights: List<String>, private val bmis: List<String>,
                    private val is_metrical_list: List<String>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView
        val heightTextView: TextView
        val weightTextView: TextView
        val bmiTextView: TextView

        init {
            dateTextView = view.findViewById(R.id.dateTextView)
            heightTextView = view.findViewById(R.id.heightTextView2)
            weightTextView = view.findViewById(R.id.weightTextView2)
            bmiTextView = view.findViewById(R.id.bmiTextView3)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_view_item, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val h_unit: String
        val w_unit: String
        if (is_metrical_list.toMutableList()[position].toBooleanStrict()) {
            h_unit = "CM"
            w_unit = "KG"
        } else {
            h_unit = "IN"
            w_unit = "LB"
        }
        val bmi = bmis.toMutableList()[position].toDouble()
        viewHolder.dateTextView.text = dates.toMutableList()[position]
        viewHolder.heightTextView.text = "Wzrost: ${heights.toMutableList()[position]} ${h_unit}"
        viewHolder.weightTextView.text = "Waga: ${weights.toMutableList()[position]} ${w_unit}"
        viewHolder.bmiTextView.text = "BMI: ${"%,.2f".format(Locale.ENGLISH, bmi)}"
        if (bmi < 18.5) {
            viewHolder.bmiTextView.setTextColor(Color.BLUE)
        } else if (bmi < 25.0) {
            viewHolder.bmiTextView.setTextColor(Color.GREEN)
        } else if (bmi < 30.0) {
            viewHolder.bmiTextView.setTextColor(Color.YELLOW)
        } else {
            viewHolder.bmiTextView.setTextColor(Color.RED)
        }
    }

    override fun getItemCount() = dates.size

}
