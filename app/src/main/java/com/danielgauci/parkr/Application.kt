package com.danielgauci.parkr

import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig



/**
 * Created by daniel on 5/4/17.
 */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        // Setup calligraphy
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Avenir-Book.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}