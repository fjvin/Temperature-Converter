package com.example.mytemperatureconverter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TempViewModel: ViewModel() {

    private var celsiusVal: Double? = null
    private var fahrenheitVal: Double? = null

    private var celsiusTemp = MutableLiveData<String>()
    private var fahrenheitTemp = MutableLiveData<String>()

    open fun getInitialCelsius(): MutableLiveData<String> {
        celsiusTemp.value = when(celsiusVal) {
            null -> ""
            else -> celsiusVal.toString()
        }
        return celsiusTemp
    }

    open fun getInitialFahrenheit(): MutableLiveData<String> {
        fahrenheitTemp.value = when (fahrenheitVal) {
            null -> ""
            else -> fahrenheitVal.toString()
        }
        return fahrenheitTemp
    }

    open fun convertTemp(inputCelsius: Double?, inputFahrenheit: Double?, roundUp: Boolean) {

        // if the inputValues are null, don't do anything
        if (inputCelsius == null && inputFahrenheit == null) {
            return
        }

        if (inputFahrenheit == null && inputCelsius != null) {
            fahrenheitVal = celsiusToFahrenheit(inputCelsius)
            if (roundUp) {
                fahrenheitVal = kotlin.math.ceil(fahrenheitVal!!)
            }
            fahrenheitTemp.value = fahrenheitVal.toString()
        }
        else if (inputFahrenheit != null && inputCelsius == null) {
            celsiusVal = fahrenheitToCelsius(inputFahrenheit)
            if (roundUp) {
                celsiusVal = kotlin.math.ceil(celsiusVal!!)
            }
            celsiusTemp.value = celsiusVal.toString()
        }
    }

    open fun resetValues() {
        celsiusVal = null
        fahrenheitVal = null
    }

    private fun celsiusToFahrenheit(inputCelsius: Double): Double {
        return (inputCelsius * 9/5) + 32
    }

    private fun fahrenheitToCelsius(inputFahrenheit: Double): Double {
        return (inputFahrenheit - 32) * 5/9
    }
}