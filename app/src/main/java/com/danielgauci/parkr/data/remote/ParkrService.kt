package com.danielgauci.parkr.data.remote

import com.danielgauci.parkr.data.model.ParkingArea
import com.danielgauci.parkr.data.model.Role
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by daniel on 5/4/17.
 */

interface ParkrService {

    @GET("parking_areas")
    fun parkingAreas(): Call<List<ParkingArea>>

    @GET("roles")
    fun roles(): Call<List<Role>>

    @GET("parking")
    fun getParkingPrediction(@Query("parking_area") parkingArea: String,
                             @Query("role") role: String,
                             @Query("day") day: String): Call<List<Double>>
}
