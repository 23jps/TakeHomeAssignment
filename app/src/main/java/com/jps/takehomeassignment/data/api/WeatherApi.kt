package com.jps.takehomeassignment.data.api

import com.jps.takehomeassignment.data.vo.WeatherData
import com.jps.takehomeassignment.utils.ApiConstants.API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    //https://api.openweathermap.org/data/2.5/weather?q=brampton&appid=a567eca4b52f20847650b5f73af13af9

    @GET("data/2.5/weather?&units=metric&appid=$API_KEY")
    fun getData(
        @Query("q") strCity: String
    ): Single<WeatherData>

}