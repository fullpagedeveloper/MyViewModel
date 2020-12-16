package com.fullpagedeveloper.myviewmodel.data.request

import com.fullpagedeveloper.myviewmodel.data.model.WeatherItemId
import com.fullpagedeveloper.myviewmodel.data.model.WeatherList
import com.fullpagedeveloper.myviewmodel.data.request.responses.ApiRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiRequest::class.java)

    fun getApiRequest(id: String, units: String, appid: String): Call<WeatherItemId> {
        return retrofitBuilder.getSearchId(id,units, appid)
    }

    fun getApiRequestW(queryName: String, appid: String): Call<WeatherList> {
        return retrofitBuilder.getSearchName(queryName, appid)
    }
}