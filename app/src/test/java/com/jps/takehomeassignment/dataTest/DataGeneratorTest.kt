package com.jps.takehomeassignment.dataTest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jps.takehomeassignment.data.vo.Main
import com.jps.takehomeassignment.data.vo.Weather
import com.jps.takehomeassignment.data.vo.WeatherData
import com.jps.takehomeassignment.data.vo.Wind
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class DataGeneratorTest {

    private var inputJson: String? = null

    @Before
    fun setUp() {
        // a result from Url
        inputJson = "{\n" +
                "  \"coord\": {\n" +
                "    \"lon\": -79.4163,\n" +
                "    \"lat\": 43.7001\n" +
                "  },\n" +
                "  \"weather\": [\n" +
                "    {\n" +
                "      \"id\": 801,\n" +
                "      \"main\": \"Clouds\",\n" +
                "      \"description\": \"few clouds\",\n" +
                "      \"icon\": \"02n\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"base\": \"stations\",\n" +
                "  \"main\": {\n" +
                "    \"temp\": 294.7,\n" +
                "    \"feels_like\": 295.29,\n" +
                "    \"temp_min\": 293.35,\n" +
                "    \"temp_max\": 295.65,\n" +
                "    \"pressure\": 1007,\n" +
                "    \"humidity\": 91\n" +
                "  },\n" +
                "  \"visibility\": 10000,\n" +
                "  \"wind\": {\n" +
                "    \"speed\": 3.6,\n" +
                "    \"deg\": 260\n" +
                "  },\n" +
                "  \"clouds\": {\n" +
                "    \"all\": 20\n" +
                "  },\n" +
                "  \"dt\": 1659417571,\n" +
                "  \"sys\": {\n" +
                "    \"type\": 2,\n" +
                "    \"id\": 2043365,\n" +
                "    \"country\": \"CA\",\n" +
                "    \"sunrise\": 1659434860,\n" +
                "    \"sunset\": 1659487204\n" +
                "  },\n" +
                "  \"timezone\": -14400,\n" +
                "  \"id\": 6167865,\n" +
                "  \"name\": \"Toronto\",\n" +
                "  \"cod\": 200\n" +
                "}"
    }

    @Test
    fun testInstantiateCurrentWeatherDataClass() {
        println("@Test - testInstantiateWeatherDataClass")
        assertTrue("Unable to instantiate WeatherData", WeatherData() != null)
    }


    //get instance from json string
    @Test
    fun testGetInstanceFromJSonString() {
        val gson = Gson()
        val result: WeatherData = gson.fromJson(inputJson, WeatherData::class.java)
        assertTrue(
            "Was unable to get CurrentWeatherData instance from Json String.",
            result is WeatherData
        )
    }

//test fields
    @Test
    fun testHasExpectedFields() {
        val builder = GsonBuilder()
        // need to register rain and snow classes
        builder.registerTypeAdapter(WeatherData::class.java, Main())
        builder.registerTypeAdapter(WeatherData::class.java, Wind())
        val gson = builder.create()
        val result: WeatherData = gson.fromJson(inputJson, WeatherData::class.java)
        // check coord
        assertTrue("Expecting lon value", result.coord?.lon!!.equals(-79.4163))
        assertTrue("Expecting lat value", result.coord?.lat!!.equals(43.7001))

    }

    //instantiate from error code
    @Test
    fun canInstantiateFromErrorJson() {
        val builder = GsonBuilder()
        val gson = builder.create()
        val inputJson = "{\"cod\":401, \"message\": \"Invalid API key. " +
                "Please see http://openweathermap.org/faq#error401 for more info.\"}"
        val cod = 401
        val result: WeatherData = gson.fromJson(inputJson, WeatherData::class.java)
        assertTrue("Expecting cod value", result.cod == cod)
    }


}