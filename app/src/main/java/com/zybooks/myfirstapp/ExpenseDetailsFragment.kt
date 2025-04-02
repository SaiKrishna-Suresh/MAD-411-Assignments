package com.zybooks.myfirstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ExpenseDetailsFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val expenseName = it.getString("expenseName") ?: "Unknown"
            val expenseAmount = it.getFloat("expenseAmount", 0f)
            val expenseDate = it.getString("expenseDate") ?: "Nil"


            view.findViewById<TextView>(R.id.detailsName).text = "Name: $expenseName"
            view.findViewById<TextView>(R.id.detailsAmount).text = "Amount: $${expenseAmount}"
            view.findViewById<TextView>(R.id.detailsDate).text = "Date: $expenseDate"
        }


    }
}