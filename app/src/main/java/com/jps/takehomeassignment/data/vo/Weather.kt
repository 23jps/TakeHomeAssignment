package com.jps.takehomeassignment.data.vo


import androidx.annotation.Keep

@Keep
data class Weather(
    val description: String? = null,
    val icon: String? = null,
    val id: Int? = null,
    val main: String? = null
)