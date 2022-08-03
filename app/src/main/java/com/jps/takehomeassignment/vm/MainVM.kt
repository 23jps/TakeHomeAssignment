package com.jps.takehomeassignment.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jps.takehomeassignment.data.api.ApiService
import com.jps.takehomeassignment.data.vo.WeatherData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class MainVM : ViewModel() {

    private val weatherApiService = ApiService()

    // This is RxJava component to Dispose our API calls Like when we want to dispose rxjava thread we need it
    private val disposable = CompositeDisposable()
    val openWeatherData = MutableLiveData<WeatherData>()
    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun getDataFromAPI(strCity: String) {
        isLoading.value = true

        // here is rxjava thread
        disposable.add(
            weatherApiService.getDataService(strCity)
                .subscribeOn(Schedulers.io()) // Scheduler.IO is threadpool
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherData>() {

                    override fun onSuccess(t: WeatherData) {
                        openWeatherData.value = t
                        isError.value = false
                        isLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        isError.value = true
                        isLoading.value = false
                    }

                })
        )
    }

    // this is called when activity destroyed
    // to avoid memory leak
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


}