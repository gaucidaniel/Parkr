package com.danielgauci.parkr.ui.parkingareadetails

import com.danielgauci.parkr.data.remote.ParkrServiceFactory
import com.danielgauci.parkr.ui.base.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by daniel on 5/5/17.
 */

class DayPredictionsPresenter : BasePresenter<DayPredictionsMvpView>(){

    val parkrService = ParkrServiceFactory.makeParkrService()

    fun getPredictions(parkingArea: String, role: String, day: String){
        // Setup view
        getMvpView().hideError()
        getMvpView().showProgressWheel(true)

        // Get predictions
        val call = parkrService.getParkingPrediction(parkingArea, role, day)
        call.enqueue(object : Callback<List<Double>>{
            override fun onResponse(call: Call<List<Double>>, response: Response<List<Double>>) {
                // Show predictions and hide other views
                getMvpView().hideError()
                getMvpView().showProgressWheel(false)
                getMvpView().showPredictions(response.body())

                // Scroll to current hour
                val calendar = Calendar.getInstance()
                val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
                getMvpView().scrollToPrediction(currentHour)
            }

            override fun onFailure(call: Call<List<Double>>, t: Throwable) {
                getMvpView().showProgressWheel(false)
                getMvpView().showError(t.message!!)
            }
        })
    }
}