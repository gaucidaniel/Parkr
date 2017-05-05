package com.danielgauci.parkr.ui.parkingareas

import com.danielgauci.parkr.data.model.ParkingArea
import com.danielgauci.parkr.data.remote.ParkrService
import com.danielgauci.parkr.data.remote.ParkrServiceFactory
import com.danielgauci.parkr.ui.base.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by daniel on 5/5/17.
 */
class ParkingAreasPresenter : BasePresenter<ParkingAreasMvpView>() {

    val parkrService: ParkrService = ParkrServiceFactory.makeParkrService()

    fun getParkingAreas(){
        // Setup views
        getMvpView().hideError()
        getMvpView().showProgressWheel(true)

        // Get list of parking areas
        val call = parkrService.parkingAreas()
        call.enqueue(object : Callback<List<ParkingArea>>{
            override fun onResponse(call: Call<List<ParkingArea>>, response: Response<List<ParkingArea>>) {
                // Update view with response
                getMvpView().showProgressWheel(false)
                getMvpView().showParkingAreas(response.body())
            }

            override fun onFailure(call: Call<List<ParkingArea>>, t: Throwable?) {
                // Update view with error
                getMvpView().hideError()
                getMvpView().showProgressWheel(false)
            }
        })
    }
}