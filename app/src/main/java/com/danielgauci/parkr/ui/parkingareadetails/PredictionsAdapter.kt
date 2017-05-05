package com.danielgauci.parkr.ui.parkingareadetails

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielgauci.parkr.R
import kotlinx.android.synthetic.main.view_prediction_item.view.*
import org.jetbrains.anko.backgroundResource
import java.util.*

/**
 * Created by daniel on 5/5/17.
 */
class PredictionsAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var mPredictions: List<Double> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return PredictionViewHolder(LayoutInflater.from(context).inflate(R.layout.view_prediction_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is PredictionViewHolder){
            holder.bind(position, mPredictions[position])
        }
    }

    override fun getItemCount(): Int {
        return mPredictions.size
    }

    fun setPredictions(predictions: List<Double>){
        mPredictions = predictions
        notifyDataSetChanged()
    }

    inner class PredictionViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(hour: Int, prediction: Double){

            // Highlight current hour
            val calendar = Calendar.getInstance()
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            itemView.predictionTimeLayout.alpha = if (hour == currentHour) 1f else 0.5f
            itemView.predictionTime.text = formatHourText(hour)
            itemView.predictionPercentage.text = "${prediction.toInt()}%"
            itemView.predictionPercentage.backgroundResource = if (prediction < 50) R.color.red else R.color.green
        }

        private fun formatHourText(hour: Int): String{
            return if (hour < 10) "0$hour:00" else "$hour:00"
        }
    }
}