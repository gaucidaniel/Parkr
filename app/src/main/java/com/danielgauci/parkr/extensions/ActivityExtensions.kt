package com.danielgauci.parkr.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Point

/**
 * Created by daniel on 5/5/17.
 */

fun Activity.getDisplayWidth(): Int{
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)

    return size.x
}