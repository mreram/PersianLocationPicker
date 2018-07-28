package com.eram.persianlocationpickerlib.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eram.persianlocationpickerlib.PersianAreaDataManger

class ProvincePersianPicker @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Spinner(context, attrs, defStyleAttr) {

    val persianAreaDataManger = PersianAreaDataManger(context)

    fun getProvinceIdByPosition(position: Int): Int {
        return persianAreaDataManger.provinceList?.get(position)?.id ?: -1
    }


    var provinceAdapter: ArrayAdapter<String>? = null

    init {
        persianAreaDataManger.initProvince()
        provinceAdapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, persianAreaDataManger.provinceList?.map { it.name })
        adapter = provinceAdapter
    }


}