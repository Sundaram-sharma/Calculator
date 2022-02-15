package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {



    var isLastNumeric: Boolean = false //tells weather the last number is numeric or not

    var isLastDecimal: Boolean = false//tells weather the last number have decimal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    //to get the value from the button display of the textview
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

    fun onEqual(view: View){

        if(isLastNumeric){ //the last value should be number only then it wil work


        }

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

    fun OnEqual(view: View){

        if(isLastNumeric)
        {
        var tvValue = tvDisplay.text.toString() //convert the value to string
        var prefix = ""

            try{

            //when the value starts with minus, we will seperate the values

                if(tvValue.startsWith("-"))
                {
                    prefix = "-"
                    tvValue = tvValue.substring(1);
                }

                //When we have division sign
                when{
                    tvValue.contains("/")->{
                        val splitValues =tvValue.split("/") //We will split the value witht the "/" sign
                        var one = splitValues[0]
                        var two = splitValues[1]

                        if(prefix.isNotEmpty()){
                        one = prefix + one // we will append the value
                        }

                        //Now we have to calculate the main login wiht this
                        // We will use remove the 'removeZeroAfterDot' to remove extra zeros

                        tvDisplay?.text =removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }

                    tvValue.contains("*")->{
                        val splitValues =tvValue.split("*") //We will split the value witht the "/" sign
                        var one = splitValues[0]
                        var two = splitValues[1]

                        if(prefix.isNotEmpty()){
                            one = prefix + one // we will append the value
                        }

                        //Now we have to calculate the main login wiht this
                        // We will use remove the 'removeZeroAfterDot' to remove extra zeros

                        tvDisplay?.text =removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }

                    tvValue.contains("+")->{ // if it contains +
                        val splitValues =tvValue.split("+") //We will split the value witht the "+" sign
                        var one = splitValues[0]
                        var two = splitValues[1]

                        if(prefix.isNotEmpty()){
                            one = prefix + one // we will append the value
                        }

                        //Now we have to calculate the main login wiht this
                        // We will use remove the 'removeZeroAfterDot' to remove extra zeros

                        tvDisplay?.text =removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }

                    tvValue.contains("-")->{
                        val splitValues =tvValue.split("-") //We will split the value with the "-" sign
                        var one = splitValues[0]
                        var two = splitValues[1]

                        if(prefix.isNotEmpty()){
                            one = prefix + one // we will append the value
                        }

                        //Now we have to calculate the main login wiht this
                        // We will use remove the 'removeZeroAfterDot' to remove extra zeros

                        tvDisplay?.text =removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }

                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }

    }

}