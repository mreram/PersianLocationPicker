package com.eram.persianlocationpickerlib.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eram.persianlocationpickerlib.PersianAreaDataManger

class CityPersianPicker @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Spinner(context, attrs, defStyleAttr) {

    var cityAdapter: ArrayAdapter<String>? = null
    var persianAreaDataManger: PersianAreaDataManger? = null


    fun getCitiesByProvinceId(id: Int): List<PersianAreaDataManger.City> {
        return persianAreaDataManger?.cityList?.filter {
            it.province_id == id
        } ?: arrayListOf()
    }

    fun getCityAdapterByProvincePosition(position: Int): ArrayAdapter<String> {
        return ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, getCitiesByProvinceId(position).map { it.name })
    }

    init {
        persianAreaDataManger = PersianAreaDataManger(context)
        persianAreaDataManger?.initCity()
        cityAdapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, persianAreaDataManger?.cityList?.map { it.name })
        adapter = cityAdapter
    }


}