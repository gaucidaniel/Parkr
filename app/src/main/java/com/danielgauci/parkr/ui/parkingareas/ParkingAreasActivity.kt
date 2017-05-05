package com.danielgauci.parkr.ui.parkingareas

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.danielgauci.parkr.R
import com.danielgauci.parkr.data.model.ParkingArea
import kotlinx.android.synthetic.main.activity_parking_areas.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class ParkingAreasActivity : AppCompatActivity(), ParkingAreasMvpView {

    lateinit var mPresenter: ParkingAreasPresenter
    lateinit var mAdapter: ParkingAreasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_areas)

        // Setup recycler view
        mAdapter = ParkingAreasAdapter(this)
        parkingAreasRecyclerView.adapter = mAdapter
        parkingAreasRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Initialize presenter
        mPresenter = ParkingAreasPresenter()
        mPresenter.attachView(this)
        mPresenter.getParkingAreas()
    }

    override fun onDestroy() {
        super.onDestroy()

        // Detach view from presenter
        mPresenter.detachView()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    override fun showParkingAreas(areas: List<ParkingArea>) {
        mAdapter.setAreas(areas = areas)
    }

    override fun showProgressWheel(boolean: Boolean) {

    }

    override fun showError(error: String) {

    }

    override fun hideError() {

    }
}
