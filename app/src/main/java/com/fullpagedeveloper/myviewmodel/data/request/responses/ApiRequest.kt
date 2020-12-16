package com.fullpagedeveloper.myviewmodel.data.request.responses

import com.fullpagedeveloper.myviewmodel.data.model.WeatherItemId
import com.fullpagedeveloper.myviewmodel.data.model.WeatherList
import retrofit2.Call
import retrofit2.http.*

interface ApiRequest {

    @GET("data/2.5/weather")
    fun getSearchName(
        @Query("q") queryName: String,
        @Query("appid") appid: String) : Call<WeatherList>

    @GET("data/2.5/group")
    fun getSearchId(
        @Query("id") id: String,
        @Query("units") units: String,
        @Query("appid") appid: String) : Call<WeatherItemId>
}