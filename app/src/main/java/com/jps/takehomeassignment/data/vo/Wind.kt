package com.jps.takehomeassignment.data.vo


import androidx.annotation.Keep

@Keep
data class Wind(
    val deg: Int? = null,
    val gust: Double? = null,
    val speed: Double? = null
)