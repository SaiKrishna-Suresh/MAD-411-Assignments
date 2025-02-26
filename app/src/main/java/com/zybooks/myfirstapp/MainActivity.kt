package com.zybooks.myfirstapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var textResult: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        editTextName = findViewById(R.id.editTextName)
        textResult = findViewById(R.id.textResult)

    }

    fun showName(view: View){
        val name = editTextName.text.toString()
        textResult.text = "Hello, $name!"

    }
}