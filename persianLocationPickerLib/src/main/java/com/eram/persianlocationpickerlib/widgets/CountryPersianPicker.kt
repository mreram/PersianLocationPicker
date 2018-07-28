package com.eram.persianlocationpickerlib.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.eram.persianlocationpickerlib.PersianAreaDataManger

class CountryPersianPicker @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Spinner(context, attrs, defStyleAttr) {
    var countryAdapter: ArrayAdapter<String>? = null

    init {
        val persianAreaDataManger = PersianAreaDataManger(context)
        persianAreaDataManger.initCountry()
        countryAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, persianAreaDataManger.countryList?.map { it.name })
        adapter = countryAdapter
    }

}