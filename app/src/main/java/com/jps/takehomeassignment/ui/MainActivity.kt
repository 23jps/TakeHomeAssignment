package com.jps.takehomeassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jps.takehomeassignment.R
import com.jps.takehomeassignment.data.vo.WeatherData
import com.jps.takehomeassignment.vm.MainVM
import com.jps.takehomeassignment.vm.factory.getViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainVM: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainVM = getViewModel()
        observers()
        callingApis()

    }

    private fun callingApis() {
        mainVM.getDataFromAPI("Toronto")
    }

    private fun observers() {
        mainVM.openWeatherData.observe(this){
            bindUi(it)
        }

        mainVM.isError.observe(this){
            displayError(it)
        }

        mainVM.isLoading.observe(this){
            showLoading(it)
        }

    }

    private fun showLoading(it: Boolean) {
        if (it) {
            rl_data.visibility = View.GONE
            tv_error.visibility = View.GONE
            pb_loading.visibility = View.VISIBLE
        }else{
            pb_loading.visibility = View.GONE
        }
    }

    private fun displayError(it: Boolean) {
        if (it) {
            rl_data.visibility = View.GONE
            pb_loading.visibility = View.GONE
            tv_error.visibility = View.VISIBLE
        }else{
            tv_error.visibility = View.GONE
        }
    }

    private fun bindUi(it: WeatherData?) {
        rl_data.visibility = View.VISIBLE
        tv_location.text = it?.name
        tv_temprature.text = it?.main?.temp?.toInt().toString() + "°C"
        tv_lowest.text = "L "+ it?.main?.temp_min?.toInt().toString() + "°C"
        tv_highest.text = "H "+ it?.main?.temp_max?.toInt().toString() + "°C"
        tv_type.text = it?.weather!![0]?.description
    }
}