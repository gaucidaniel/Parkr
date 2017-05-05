package com.danielgauci.parkr.ui.parkingareas

import com.danielgauci.parkr.data.model.ParkingArea
import com.danielgauci.parkr.ui.base.MvpView

/**
 * Created by daniel on 5/5/17.
 */
interface ParkingAreasMvpView : MvpView{

    fun showParkingAreas(areas: List<ParkingArea>)

    fun showProgressWheel(boolean: Boolean)

    fun showError(error: String)

    fun hideError()
}