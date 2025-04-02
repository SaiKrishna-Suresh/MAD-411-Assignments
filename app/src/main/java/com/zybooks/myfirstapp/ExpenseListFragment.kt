package com.zybooks.myfirstapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ExpenseListFragment : Fragment() {
    private lateinit var expenseName : EditText
    private lateinit var expenseAmount: EditText
    private lateinit var expenseDate: EditText
    private lateinit var expenseButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var browserButton: Button
    private lateinit var footerFragment:FooterFragment

    private val expenseList = mutableListOf<Expense>()
    private lateinit var adapter: ExpenseAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenseName = view.findViewById(R.id.expenseName)
        expenseAmount = view.findViewById(R.id.expenseAmount)
        expenseDate = view.findViewById(R.id.expenseDate)
        expenseButton = view.findViewById(R.id.expenseButton)
        browserButton = view.findViewById(R.id.browserButton)
        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
         adapter = ExpenseAdapter(expenseList,findNavController())
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
            requireActivity().openFileOutput("expense.json", MODE_PRIVATE).use {
                it.write(json.toByteArray())
            }
            Log.d("File Storage","Expenses Saved Succesfully")
        } catch (e: Exception) {
            Log.e("Main Activity", "Error saving expenses", e)
        }
    }

    private fun loadToFile() {

        try {
            val input =requireActivity().openFileInput("expense.json")
                val json = input.bufferedReader().readText()
                val gson = Gson()
                val expenseListType = object : TypeToken<List<Expense>>() {}.type
                expenseList.addAll(gson.fromJson(json, expenseListType))


        } catch (e: Exception) {
            Log.e("MainActivity", "Error reading expenses", e)
        }


    }



    private fun updateTotalExpense(expenseList: List<Expense>){
        val totalAmount = expenseList.sumOf { it.amount }
        footerFragment.updateTotalAmount(totalAmount)
    }

    private fun addFragment(fragment: Fragment,containerId: Int){
        childFragmentManager.beginTransaction()
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



}