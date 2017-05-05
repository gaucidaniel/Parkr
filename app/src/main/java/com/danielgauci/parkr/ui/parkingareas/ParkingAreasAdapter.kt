package com.danielgauci.parkr.ui.parkingareas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielgauci.parkr.R
import com.danielgauci.parkr.data.model.ParkingArea
import com.danielgauci.parkr.ui.parkingareadetails.ParkingAreaDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_parking_area.view.*

/**
 * Created by daniel on 5/5/17.
 */
class ParkingAreasAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var mAreaList: List<ParkingArea> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return AreaViewHolder(LayoutInflater.from(context).inflate(R.layout.view_parking_area, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AreaViewHolder){
//            val params = holder.itemView.parkingAreaCard.layoutParams as ViewGroup.MarginLayoutParams
//            params.leftMargin = context.resources.getDimension(if (position == 0) R.dimen.spacing_huge else R.dimen.spacing_large).toInt()
//            holder.itemView.parkingAreaCard.layoutParams = params

            holder.bind(mAreaList[position])
        }
    }

    override fun getItemCount(): Int {
        return mAreaList.size
    }

    fun setAreas(areas: List<ParkingArea>){
        mAreaList = areas
        notifyDataSetChanged()

    }

    inner class AreaViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(area: ParkingArea){
            itemView.setOnClickListener {
                val intent = Intent(context, ParkingAreaDetailsActivity::class.java)
                intent.putExtra(ParkingAreaDetailsActivity.EXTRA_PARKING_AREA, area)
                context.startActivity(intent)
                (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }

            // Load image
            with(area) {
                val url = "https://maps.googleapis.com/maps/api/staticmap?" +
                        "center=${location.latitude},${location.longitude}&zoom=$zoom&scale=2" +
                        "&size=600x600&maptype=satellite" +
                        "&key=AIzaSyCImujxFA4fyz_Ds2aF2T3Uq9dEUmerMh8&format=png&visual_refresh=true"

                Picasso.with(context).load(url).into(itemView.parkingAreaImage)
                itemView.parkingAreaTitle.text = name
                itemView.parkingAreaDescription.text = region
            }
        }
    }
}