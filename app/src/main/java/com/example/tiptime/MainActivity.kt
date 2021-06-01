package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.round
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val amazingTip = 0.2
        val goodTip = 0.15
        val okTip = 0.1
        val calcBtn: Button = findViewById(R.id.calcBtn)
        val totalPrice: TextView = findViewById(R.id.totalPrice)
        val beforeTip: TextView = findViewById(R.id.beforeTip)
        val tipAmount: TextView = findViewById(R.id.tipAmount)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val serviceCost: EditText = findViewById(R.id.serviceCost)
        val roundUp: Switch = findViewById(R.id.roundSwitch)



        calcBtn.setOnClickListener {
            if(validateInput(serviceCost.text.toString()))
            {
//                default value for tip is 0.1
                var  currentTip: Double = okTip
                when (radioGroup.checkedRadioButtonId) {
                    R.id.amazingRadio -> currentTip = amazingTip
                    R.id.goodRadio -> currentTip = goodTip
                    R.id.okRadio -> currentTip = okTip
                }

                var total = calculateTipAmount(serviceCost.text.toString().toDouble(), currentTip)

                // round it to int
                if (roundUp.isChecked)
                    total = ceil(total)

                beforeTip.text = serviceCost.text.toString()
                tipAmount.text = "${currentTip*100}%"
                totalPrice.text = total.toString()

            }
        }

    }

    private fun validateInput(input: String): Boolean {
        if(input == "") {
            Toast.makeText(this, "Cost of Service is empty", Toast.LENGTH_SHORT).show()
            return false
        }
        else if(input.toDouble() <= 0) {
            Toast.makeText(this, "Can't tip with this amount", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun calculateTipAmount(cost: Double, currentTip: Double): Double {
        return cost + round(cost*currentTip * 10000)/10000
    }


}