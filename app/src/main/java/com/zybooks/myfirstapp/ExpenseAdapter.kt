package com.zybooks.myfirstapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class ExpenseAdapter (
    private val expenseList: MutableList<MainActivity.Expense>):
        RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseAdapter.ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expense_item,parent,false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ExpenseAdapter.ExpenseViewHolder,
        position: Int,
    ) {
        val expense = expenseList[position]
        holder.name.text = expense.name
        holder.amount.text = "$${expense.amount}"
        holder.date.text = expense.date

        holder.deleteButton.setOnClickListener{
            expenseList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,expenseList.size)
        }
        holder.detailsButton.setOnClickListener{

            val intent = Intent(holder.itemView.context,ExpenseDetailsActivity::class.java).apply{
                putExtra("expenseName",expense.name)
                putExtra("expenseAmount",expense.amount)
                putExtra("expenseDate",expense.date)
            }
            holder.itemView.context.startActivity(intent)
        }



    }

    override fun getItemCount(): Int {
        return expenseList.size
    }
    class ExpenseViewHolder(view:View):RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.nameView)
        val amount: TextView = view.findViewById(R.id.amountView)
        val date: TextView = view.findViewById(R.id.dateView)
        val deleteButton:Button = view.findViewById(R.id.deleteButton)
        val detailsButton:Button = view.findViewById(R.id.detailsButton)

    }

}