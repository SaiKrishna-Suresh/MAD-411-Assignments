package com.zybooks.myfirstapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ExpenseDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_expense_details)

        val expenseName = intent.getStringExtra("expenseName")
        val expenseAmount = intent.getDoubleExtra("expenseAmount",0.0)
        val expenseDate = intent.getStringExtra("expenseDate")?:"Nil"

        findViewById<TextView>(R.id.detailsName).text = "Name: $expenseName"
        findViewById<TextView>(R.id.detailsAmount).text = "Amount: $${expenseAmount}"
        findViewById<TextView>(R.id.detailsDate).text = "Date: $expenseDate"

    }
}