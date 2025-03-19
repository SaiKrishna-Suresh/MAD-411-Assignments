package com.zybooks.myfirstapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var expenseName : EditText
    private lateinit var expenseAmount: EditText
    private lateinit var expenseDate: EditText
    private lateinit var expenseButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var browserButton:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("ActivityLifeCycle","onCreate called")

        expenseName = findViewById(R.id.expenseName)
        expenseAmount = findViewById(R.id.expenseAmount)
        expenseDate = findViewById(R.id.expenseDate)
        expenseButton = findViewById(R.id.expenseButton)
        browserButton = findViewById(R.id.browserButton)
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

        browserButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.finanacialtips.com"))
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("ActivityLifeCycle","onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ActivityLifeCycle","onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ActivityLifeCycle","onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ActivityLifecycle","onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ActivityLifecycle","onDestroy called")
    }

    class Expense (val name:String, val amount:Double,val date:String)

}