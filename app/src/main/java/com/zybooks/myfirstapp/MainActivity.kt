package com.zybooks.myfirstapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {
    private lateinit var expenseName : EditText
    private lateinit var expenseAmount: EditText
    private lateinit var expenseDate: EditText
    private lateinit var expenseButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var browserButton:Button
    private lateinit var footerFragment:FooterFragment

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("ActivityLifeCycle", "onCreate called")

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

        loadToFile()

        addFragment(HeaderFragment(), R.id.headerContainer)
        footerFragment = FooterFragment()
        addFragment(footerFragment, R.id.footerContainer)



        expenseButton.setOnClickListener {
            val name = expenseName.text.toString()
            val amount = expenseAmount.text.toString()
            val date = expenseDate.text.toString()

            val expense = Expense(name, amount.toDouble(), date)
            expenseList.add(expense)
            adapter.notifyItemInserted(expenseList.size)

            saveToFile(expenseList)

            expenseName.text.clear()
            expenseAmount.text.clear()
            expenseDate.text.clear()

            updateTotalExpense(expenseList)


        }


        browserButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.financialexpress.com/"))
            startActivity(intent)
        }
    }
    private fun saveToFile(expenses: List<Expense>) {
        val gson = Gson()
        val json = gson.toJson(expenses)

        try {
            openFileOutput("expenses.json", MODE_PRIVATE).use { output ->
                output.write(json.toByteArray())
            }
            Log.d("File Storage","Expenses Saved Succesfully")
        } catch (e: Exception) {
            Log.e("Main Activity", "Error saving expenses", e)
        }
    }

    private fun loadToFile(): List<Expense> {
        val expenses = mutableListOf<Expense>()
        try {
            openFileInput("expenses.json").use { input ->
                val json = input.bufferedReader().readText()
                val gson = Gson()
                val expenseListType = object : TypeToken<List<Expense>>() {}.type
                expenses.addAll(gson.fromJson(json, expenseListType))

            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error reading expenses", e)
        }
        return expenses

    }



    private fun updateTotalExpense(expenseList: List<Expense>){
            val totalAmount = expenseList.sumOf { it.amount }
            footerFragment.updateTotalAmount(totalAmount)
        }

        private fun addFragment(fragment: Fragment,containerId: Int){
            supportFragmentManager.beginTransaction()
                .replace(containerId,fragment)
                .commit()
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