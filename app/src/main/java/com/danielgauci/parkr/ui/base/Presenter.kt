package com.danielgauci.parkr.ui.base

/**
 * Created by daniel on 5/5/17.
 */
interface Presenter<in V: MvpView> {

    fun attachView(mvpView: V)
    fun detachView()
}