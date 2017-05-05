package com.danielgauci.parkr.ui.base

/**
 * Created by daniel on 5/5/17.
 */
open class BasePresenter<V: MvpView> : Presenter<V> {

    private var mMvpView: V? = null

    override fun attachView(mvpView: V) {
        mMvpView = mvpView
    }

    override fun detachView() {
        mMvpView = null
    }

    fun isViewAttached() : Boolean{
        return mMvpView != null
    }

    fun checkViewAttached(){
        if (!isViewAttached()){
            throw Exception("Attach view before using presenter")
        }
    }

    fun getMvpView(): V{
        checkViewAttached()
        return mMvpView!!
    }
}