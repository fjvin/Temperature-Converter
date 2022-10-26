package com.example.mytemperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mytemperatureconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tempViewModel: TempViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tempViewModel = ViewModelProvider(this)[TempViewModel::class.java]

        /* Update Celsius TextView Value */
        var celsiusVal: LiveData<String> = tempViewModel.getInitialCelsius()
        celsiusVal.observe(this, Observer {
            binding.celsiusTemp.setText(it)
        })
        /* Update Fahrenheit TextView Value */
        var fahrenheitVal: LiveData<String> = tempViewModel.getInitialFahrenheit()
        fahrenheitVal.observe(this, Observer {
            binding.fahrenheitTemp.setText(it)
        })

        binding.convertBtn.setOnClickListener { this.convertTemp() }
        binding.resetBtn.setOnClickListener { this.resetInputs() }

    }

    private fun convertTemp() {
        val inputCelsius = binding.celsiusTemp.text.toString().toDoubleOrNull();
        val inputFahrenheit = binding.fahrenheitTemp.text.toString().toDoubleOrNull();
        val roundUp = binding.roundUp.isChecked
        tempViewModel.convertTemp(inputCelsius, inputFahrenheit, roundUp)
    }

    private fun resetInputs() {
        /* clear the text from edit text */
        tempViewModel.resetValues()
        binding.fahrenheitTemp.setText("")
        binding.celsiusTemp.setText("")
        return
    }



}