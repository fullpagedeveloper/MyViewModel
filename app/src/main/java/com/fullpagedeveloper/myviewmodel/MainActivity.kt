package com.fullpagedeveloper.myviewmodel

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fullpagedeveloper.myviewmodel.search.WeatherAdapter
import com.fullpagedeveloper.myviewmodel.search.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: WeatherAdapter
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherViewModel = ViewModelProvider(
            this@MainActivity,
            ViewModelProvider.NewInstanceFactory()
        )[WeatherViewModel::class.java]

        adapter = WeatherAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)

        setWeather(editCity.text.toString().trim())

        btnCity.setOnClickListener {
            val id = editCity.text.toString().trim()
            //if (id.isEmpty()) return@setOnClickListener
            showLoading(true)
            setWeather(id)
        }
    }

    private fun setWeather(id: String) {
        if (id.isEmpty()) {
            Toast.makeText(this, "kosong", Toast.LENGTH_SHORT).show()
            showLoading(false)
        } else {
            weatherViewModel.getWeather(id).observe(this, { weatherItems ->
                weatherViewModel.getLisWeather(weatherItems.id.toString()).observe(this, { ada ->
                    if (weatherItems != null) {
                        adapter.setData(ArrayList(ada.list))
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                        showLoading(false)
                    }
                })
            })
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}