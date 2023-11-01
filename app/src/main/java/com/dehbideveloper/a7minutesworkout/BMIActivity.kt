package com.dehbideveloper.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dehbideveloper.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    var binding : ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            if (validateMetricUnits()){
                val heightValue : Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
                val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi = weightValue / Math.pow(heightValue.toDouble(), 2.0)

                displayBMIResult(bmi.toFloat())


            } else {
                Toast.makeText(this@BMIActivity, "Please enter valid values", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayBMIResult(bmi: Float){

        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0){
            bmiLabel = "Very severly underwight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
            bmiLabel = "severly underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself ! Eat more"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Nomral"
            bmiDescription ="Congratulation you're in the normal shape"
        } else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0){
            bmiLabel = "Overweight!"
            bmiDescription = "Oops! You really need to take care of yourself! Workout"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0){
            bmiLabel = "Obese Class | (Moderately Obese)"
            bmiDescription = "Oops! You really need to take care of yourself! Workout"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0){
            bmiLabel = "Obese Class || (Severly Obese)"
            bmiDescription = "OMG! You are in very dangerous condition! Act Now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severly Obese)"
            bmiDescription = "OMG! You are in very dangerous condition! Act Now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription

    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding?.etMetricUnitHeight?.text.toString().isEmpty()) isValid = false
        else if (binding?.etMetricUnitWeight?.text.toString().isEmpty()) isValid = false

        return isValid
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}