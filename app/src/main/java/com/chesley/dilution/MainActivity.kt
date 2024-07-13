package com.chesley.dilution

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var edtMl: TextInputLayout
    private lateinit var edtSolut: TextInputLayout
    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtMl = findViewById(R.id.edtMl)
        edtSolut = findViewById(R.id.edtSolut)
        txtResult = findViewById(R.id.txtResult)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateDilution()
            }

            override fun afterTextChanged(s: Editable?) {}
            }

        edtMl.editText?.addTextChangedListener(textWatcher)
        edtSolut.editText?.addTextChangedListener(textWatcher)
        }

    private fun calculateDilution() {
        val mlText = edtMl.editText?.text.toString()
        val dilutionText = edtSolut.editText?.text.toString()

        if (mlText.isNotEmpty() && dilutionText.isNotEmpty()) {
            try {
                val ml = mlText.toDouble()
                val denominator = dilutionText.toDouble()
                val result = ml / (denominator / 1)
                txtResult.text = "Utilize %.2f ml".format(result) + " de produto concentrado."
            } catch (e: NumberFormatException) {
                txtResult.text = "Formato de número inválido"
            }
        } else {
            txtResult.text = "Utilize __ ml de produto concentrado."
        }
    }
}