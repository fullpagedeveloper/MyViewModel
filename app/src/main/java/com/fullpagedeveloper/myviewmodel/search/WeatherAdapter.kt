package com.fullpagedeveloper.myviewmodel.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fullpagedeveloper.myviewmodel.R
import com.fullpagedeveloper.myviewmodel.data.model.WeatherList
import kotlinx.android.synthetic.main.weather_items.view.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val mData = ArrayList<WeatherList>()

    fun setData(items: ArrayList<WeatherList>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_items, parent, false)
        return WeatherViewHolder(mView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(list: WeatherList) {
            with(itemView) {
                textCity.text = list.name
                textTemp.text = list.main.temp.toString()

                for (getList in list.weather) {
                    textDesc.text = getList.description
                }
            }
        }

    }
}
