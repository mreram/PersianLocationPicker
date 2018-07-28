package com.eram.persianlocationpicker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spProvince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val provinceIdByPosition = spProvince.getProvinceIdByPosition(position)
                spCity.adapter = spCity.getCityAdapterByProvincePosition(provinceIdByPosition)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }
}
