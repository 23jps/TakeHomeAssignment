package com.jps.takehomeassignment.data.api

import com.jps.takehomeassignment.data.vo.WeatherData
import com.jps.takehomeassignment.utils.ApiConstants.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiService {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    fun getDataService(strCity: String): Single<WeatherData> {
        return api.getData(strCity)
    }

}