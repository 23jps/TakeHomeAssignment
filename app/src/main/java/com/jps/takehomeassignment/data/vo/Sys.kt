package com.jps.takehomeassignment.data.vo


import androidx.annotation.Keep

@Keep
data class Sys(
    val country: String? = null,
    val sunrise: Int? = null,
    val sunset: Int? = null
)