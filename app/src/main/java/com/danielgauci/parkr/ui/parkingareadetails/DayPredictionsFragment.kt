package com.danielgauci.parkr.ui.parkingareadetails


import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog

import com.danielgauci.parkr.R
import kotlinx.android.synthetic.main.fragment_day_predictions.*

class DayPredictionsFragment : Fragment(), DayPredictionsMvpView {

    companion object {
        private val ARG_PARKING_SPACE_ID = "parkingSpace"
        private val ARG_DAY = "day"

        fun newInstance(parkingSpaceId: String, day: String): DayPredictionsFragment {
            val fragment = DayPredictionsFragment()
            val args = Bundle()
            args.putString(ARG_PARKING_SPACE_ID, parkingSpaceId)
            args.putString(ARG_DAY, day)
            fragment.arguments = args
            return fragment
        }
    }

    private var mAdapter: PredictionsAdapter? = null
    private var mLayoutManager: LinearLayoutManager? = null
    private var mParkingSpace: String? = null
    private var mDay: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParkingSpace = arguments.getString(ARG_PARKING_SPACE_ID)
            mDay = arguments.getString(ARG_DAY)

            // Get current role
            val prefs = PreferenceManager.getDefaultSharedPreferences(activity)
            val role = prefs.getString("role", "student")

            // Setup Presenter
            val presenter = DayPredictionsPresenter()
            presenter.attachView(this)
            presenter.getPredictions(mParkingSpace!!, role, mDay!!)
        } else {
            throw Exception("${this.javaClass.name} started without parameters")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_day_predictions, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        mAdapter = PredictionsAdapter(context)
        mLayoutManager = LinearLayoutManager(activity)
        dayPredictionsRecyclerView.adapter = mAdapter
        dayPredictionsRecyclerView.layoutManager = mLayoutManager
    }

    override fun showPredictions(predictions: List<Double>) {
        mAdapter?.setPredictions(predictions)
    }

    override fun scrollToPrediction(position: Int) {
        if (dayPredictionsRecyclerView != null && mLayoutManager != null) {
            val visibleItems = mLayoutManager!!.findLastCompletelyVisibleItemPosition() -
                    mLayoutManager!!.findFirstCompletelyVisibleItemPosition()

            val offsetPosition = position + (visibleItems / 2)
            dayPredictionsRecyclerView.scrollToPosition(
                    if (offsetPosition >= mAdapter!!.itemCount){
                        mAdapter!!.itemCount - 1
                    } else {
                        offsetPosition
                    }
            )
        }
    }

    override fun showError(error: String) {
        MaterialDialog.Builder(context)
                .title("Error")
                .content(error)
                .positiveText("Continue")
                .positiveColor(R.color.colorAccent)
                .build()
                .show()
    }

    override fun hideError() {

    }

    override fun showProgressWheel(show: Boolean) {
        if (dayPredictionsProgressWheel != null ) {
            dayPredictionsProgressWheel.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}