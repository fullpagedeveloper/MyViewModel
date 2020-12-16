package com.fullpagedeveloper.myviewmodel.search

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fullpagedeveloper.myviewmodel.data.model.WeatherItemId
import com.fullpagedeveloper.myviewmodel.data.model.WeatherList
import com.fullpagedeveloper.myviewmodel.data.request.Constants
import com.fullpagedeveloper.myviewmodel.data.request.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class WeatherViewModel: ViewModel() {

    private val weatherList = MutableLiveData<WeatherList>()
    private val weather = MutableLiveData<WeatherItemId>()
    private val getApiServiceGenerator = ServiceGenerator()

    fun getLisWeather(id: String) : MutableLiveData<WeatherItemId> {
        getApiServiceGenerator.getApiRequest(id, "metric", Constants.API_KEY).enqueue(
            object : Callback<WeatherItemId> { override fun onResponse(call: Call<WeatherItemId>, response: Response<WeatherItemId>) {
                    if (response.code() == 200) {
                        Log.d("TAG", "onResponse: ${response.body().toString()}")
                        weather.value = response.body()
                    } else {
                        try {
                            Log.d("TAG", "onResponse: ${response.errorBody().toString()}")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherItemId>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                    println(t.fillInStackTrace())
                }

            })
        return weather
    }
    
    fun getWeather(idd: String) : MutableLiveData<WeatherList> {
        getApiServiceGenerator.getApiRequestW(idd, Constants.API_KEY).enqueue(object :
            Callback<WeatherList> {
            override fun onResponse(call: Call<WeatherList>, response: Response<WeatherList>) {
                if (response.code() == 200) {
                    weatherList.value = response.body()
                } else {
                    try {
                        Log.d("TAG", "onResponse: ${response.errorBody().toString()}")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<WeatherList>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
                println(t.fillInStackTrace())
            }

        })
        return  weatherList
    }
}