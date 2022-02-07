package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    var isLastNumeric: Boolean = false //tells weather the last number is numeric or not

    var isLastDecimal: Boolean = false//tells weather the last number have decimal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //to get the vale from the button display of the textview
    fun getDigit(view: View){
        tvDisplay.append((view as Button).text)

        isLastNumeric = true
    }

    fun getDecimal(view: View){

        if(isLastNumeric && !isLastDecimal){

            tvDisplay.append(".")
            isLastDecimal = true
            isLastNumeric = false

        }



    }

    fun onOperator(view: View){
        Log.d("finder1","this is working")
        if(isLastNumeric&&!isOperatorAdded(tvDisplay.text.toString())){

            // enter the same value of the button
                Log.d("finder2","this is working")
            tvDisplay.append((view as Button).text)
            Log.d("finder3","this is working")
            isLastNumeric = false // update the value
            isLastDecimal = false // update the value

        }

    }

    fun onEqualsTo(view: View){

    }

    //function to clear the screen
    fun clearScreen(view: View){
        tvDisplay.text = ""
        isLastNumeric = false
        isLastDecimal = false

    }

    private fun isOperatorAdded(value: String): Boolean {

        /**
         * Here first we will check that if the value starts with "-" then will ignore it.
         * As it is the result value and perform further calculation.
         */

        return if (value.startsWith("-")) {
            false
        } else {
            (value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+"))
        }
    }

    //remove zero after decimal
    /**
     * Remove the zero after decimal point
     */
    private fun removeZeroAfterDot(result: String): String {

        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }


}