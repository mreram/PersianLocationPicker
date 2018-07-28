package com.eram.persianlocationpickerlib

import android.content.Context
import android.content.ContextWrapper
import org.json.JSONArray

open class PersianAreaDataManger(private val context: Context) : ContextWrapper(context) {


    var provinceList: MutableList<Province>? = null
    var cityList: MutableList<City>? = null
    var countryList: MutableList<Country>? = null


    data class City(val id: Int, val province_id: Int, val slug: String, val name: String, val latitude: Double, val longitude: Double)
    data class Province(val id: Int, val slug: String, val name: String, val latitude: Double, val longitude: Double)
    data class Country(val name: String)



    open fun initCountry(): MutableList<Country>? {
        countryList = mutableListOf()

        val countryJsonFile = context.assets.open("countries.json")
        val countryJsonSize = countryJsonFile.available()
        val countryJsonBuffer = ByteArray(countryJsonSize)
        countryJsonFile.read(countryJsonBuffer)
        countryJsonFile.close()

        val countryJsonArray = JSONArray(String(countryJsonBuffer, Charsets.UTF_8))

        for (i in 0 until countryJsonArray.length()) {

            val countryJOBJ = countryJsonArray.getJSONObject(i)
            val countryObj = Country(
                    countryJOBJ.getString("name")
            )
            countryList?.add(countryObj)

        }

        return countryList
    }

    open fun initCity(): MutableList<City>? {
        val cityJsonFile = context.assets.open("cities.json")
        val cityJsonSize = cityJsonFile.available()
        val cityBuffer = ByteArray(cityJsonSize)
        cityJsonFile.read(cityBuffer)
        cityJsonFile.close()

        val cityJsonArray = JSONArray(String(cityBuffer, Charsets.UTF_8))
        cityList = mutableListOf()

        for (i in 0 until cityJsonArray.length()) {

            val cityJOBJ = cityJsonArray.getJSONObject(i)
            val cityObj = City(
                    cityJOBJ.getInt("id"),
                    cityJOBJ.getInt("province_id"),
                    cityJOBJ.getString("slug")
                    , cityJOBJ.getString("title"),
                    cityJOBJ.getDouble("latitude"),
                    cityJOBJ.getDouble("longitude"))
            cityList?.add(cityObj)
        }

        return cityList
    }

    open fun initProvince(): MutableList<Province>? {

        val provinceJsonFile = context.assets.open("province.json")
        val provinceJsonSize = provinceJsonFile.available()
        val provinceJsonBuffer = ByteArray(provinceJsonSize)
        provinceJsonFile.read(provinceJsonBuffer)
        provinceJsonFile.close()

        val provinceJsonArray = JSONArray(String(provinceJsonBuffer, Charsets.UTF_8))
        provinceList = mutableListOf()

        for (i in 0 until provinceJsonArray.length()) {

            val provinceJOBJ = provinceJsonArray.getJSONObject(i)
            val provinceObj = Province(
                    provinceJOBJ.getInt("id"),
                    provinceJOBJ.getString("slug")
                    , provinceJOBJ.getString("title"),
                    provinceJOBJ.getDouble("latitude"),
                    provinceJOBJ.getDouble("longitude"))
            provinceList?.add(provinceObj)

        }

        return provinceList
    }

    open fun getCountry(): MutableList<Country>? {
        if (countryList != null)
            return countryList
        else {
            throw NotCalledInitCountryMethod()
        }
    }

    open fun getCitiesByProvinceId(province_id: Int): List<City> {
        if (cityList != null)
            return cityList?.filter { it.province_id == province_id } ?: mutableListOf()
        else {
            throw NotCalledInitProvinceAndCityMethod()
        }
    }


    class NotCalledInitCountryMethod : Exception() {
        override val message: String?
            get() = NotCalledInitCountryMethod::class.java.canonicalName
    }

    class NotCalledInitProvinceAndCityMethod : Exception() {
        override val message: String?
            get() = NotCalledInitProvinceAndCityMethod::class.java.canonicalName
    }


}