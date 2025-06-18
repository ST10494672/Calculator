package com.example.calculatorapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.calculator.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber1 = findViewById<EditText>(R.id.editTextNumber1)
        val editTextNumber2 = findViewById<EditText>(R.id.editTextNumber2)
        val spinnerOperations = findViewById<Spinner>(R.id.spinnerOperations)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        // Setup Spinner
        val operations = arrayOf("+", "−", "×", "÷")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOperations.adapter = adapter

        buttonCalculate.setOnClickListener {
            try {
                val num1 = editTextNumber1.text.toString().toDouble()
                val num2 = editTextNumber2.text.toString().toDouble()
                val operation = spinnerOperations.selectedItem.toString()

                val result = when (operation) {
                    "+" -> num1 + num2
                    "−" -> num1 - num2
                    "×" -> num1 * num2
                    "÷" -> {
                        if (num2 == 0.0) {
                            throw ArithmeticException("Cannot divide by zero")
                        }
                        num1 / num2
                    }
                    else -> throw IllegalArgumentException("Unknown operation")
                }

                textViewResult.text = "Result: $result"
            } catch (e: NumberFormatException) {
                textViewResult.text = "Error: Please enter valid numbers"
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            } catch (e: ArithmeticException) {
                textViewResult.text = "Error: ${e.message}"
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                textViewResult.text = "Error: ${e.message}"
                Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }
}