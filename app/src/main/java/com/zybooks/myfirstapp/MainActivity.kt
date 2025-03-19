package com.zybooks.myfirstapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var expenseName : EditText
    private lateinit var expenseAmount: EditText
    private lateinit var expenseDate: EditText
    private lateinit var expenseButton: Button
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expenseName = findViewById(R.id.expenseName)
        expenseAmount = findViewById(R.id.expenseAmount)
        expenseDate = findViewById(R.id.expenseDate)
        expenseButton = findViewById(R.id.expenseButton)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val expenseList = mutableListOf<Expense>()
        val adapter = ExpenseAdapter(expenseList)
        recyclerView.adapter = adapter

        expenseButton.setOnClickListener{
            val name = expenseName.text.toString()
            val amount = expenseAmount.text.toString()
            val date = expenseDate.text.toString()

            val expense = Expense(name,amount.toDouble(),date)
            expenseList.add(expense)
            adapter.notifyItemInserted(expenseList.size)

            expenseName.text.clear()
            expenseAmount.text.clear()
            expenseDate.text.clear()

        }

    }

    class Expense (val name:String, val amount:Double,val date:String)

}