package com.example.mytemperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytemperatureconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.convertBtn.setOnClickListener { this.convertTemp() }
        binding.resetBtn.setOnClickListener { this.resetInputs() }
    }

    private fun convertTemp() {
        /* convert temperature based on checked conversion method */

        // get input value
        val celsiusVal = binding.celsiusTemp.text.toString().toDoubleOrNull()
        val fahrenheitVal = binding.fahrenheitTemp.text.toString().toDoubleOrNull()
        var convertedValue: Double
        val isRoundUpChecked = binding.roundUp.isChecked

        // if the inputValue is null, then display empty string
        if (celsiusVal == null && fahrenheitVal == null) {
            resetInputs()
            return
        }

        if (celsiusVal != null &&  fahrenheitVal == null) {
            convertedValue = celsiusToFahrenheit(celsiusVal)
            if (isRoundUpChecked) { convertedValue = kotlin.math.ceil(convertedValue) }
            binding.fahrenheitTemp.setText(convertedValue.toString())

        } else if (celsiusVal == null &&  fahrenheitVal != null) {
            convertedValue = fahrenheitToCelsius(fahrenheitVal)
            if (isRoundUpChecked) { convertedValue = kotlin.math.ceil(convertedValue) }
            binding.celsiusTemp.setText(convertedValue.toString())
        }

    }

    private fun celsiusToFahrenheit(value: Double): Double {
        /* °F = (°C × 9/5) + 32 */
        return (value * 9/5) + 32
    }

    private fun fahrenheitToCelsius(value: Double): Double {
        /* °C = (°F − 32) x 5/9 */
        return (value - 32) * 5/9
    }

    private fun resetInputs() {
        /* clear the text from edit text */
        binding.fahrenheitTemp.setText("")
        binding.celsiusTemp.setText("")
        return
    }

}