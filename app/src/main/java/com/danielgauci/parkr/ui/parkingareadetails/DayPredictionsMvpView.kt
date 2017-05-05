package com.danielgauci.parkr.ui.parkingareadetails

import com.danielgauci.parkr.ui.base.MvpView

/**
 * Created by daniel on 5/5/17.
 */
interface DayPredictionsMvpView : MvpView {

    fun showPredictions(predictions: List<Double>)

    fun showError(error: String)

    fun hideError()

    fun showProgressWheel(show: Boolean)

    fun scrollToPrediction(position: Int)
}