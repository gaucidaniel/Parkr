package com.danielgauci.parkr.ui.parkingareadetails

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.danielgauci.parkr.R
import com.danielgauci.parkr.data.model.ParkingArea
import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_parking_area_details.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.util.*


class ParkingAreaDetailsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_PARKING_AREA = "PARKING_AREA"
    }

    lateinit var mParkingArea: ParkingArea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_area_details)

        // Get parking area from intent
        mParkingArea = intent.getParcelableExtra(EXTRA_PARKING_AREA)

        // Setup toolbar items
        with(mParkingArea) {
            areaDetailsName.text = name
            areaDetailsNavigate.setOnClickListener {
                launchNavigation(mParkingArea.location.latitude, mParkingArea.location.longitude)
            }
        }

        areaDetailsBack.setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        setupViewPager()
    }

    private fun setupViewPager(){
        areaDetailsViewPager.adapter = DaysPagerAdapter(supportFragmentManager, mParkingArea)
        areaDetailsTabLayout.setupWithViewPager(areaDetailsViewPager)

        // Scroll to current day (offset to start from monday
        val calendar = Calendar.getInstance()
        val currentDay = calendar.get(Calendar.DAY_OF_WEEK) - 2
        areaDetailsViewPager.currentItem = if (currentDay == -1) 6 else currentDay
    }

    private fun launchNavigation(latitude: Double, longitude: Double) {
        val gmmIntentUri = Uri.parse("geo:$latitude, $longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.`package` = "com.google.android.apps.maps"
        startActivity(mapIntent)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        super.onBackPressed()
    }

    inner class DaysPagerAdapter(fm: FragmentManager, val parkingArea: ParkingArea) : FragmentStatePagerAdapter(fm){

        val days = arrayOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday")

        override fun getItem(position: Int): Fragment {
            return DayPredictionsFragment.newInstance(parkingArea.id, days[position])
        }

        override fun getCount(): Int {
            return days.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return days[position]
        }
    }
}
