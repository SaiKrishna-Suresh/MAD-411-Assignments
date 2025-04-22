package com.zybooks.exammock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter (
    private val habitList:MutableList<Habit>,
    private val navController: NavController):
        RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ):
            HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.habit_item, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HabitViewHolder,
        position: Int,

        ) {
        val habit = habitList[position]
        holder.habitsName.text = habit.name
        holder.goal.text = habit.goal
        holder.time.text = habit.time

        holder.habitButton.setOnClickListener{
            val bundle = Bundle().apply {
                putString("habitName",habit.name)
                putString("habitGoal",habit.goal)
                putString("habitTime",habit.time)
            }
            navController.navigate(R.id.action_habitListFragment_to_habitFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return habitList.size
    }


    class HabitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val habitsName: TextView = view.findViewById(R.id.nameView)
        val goal: TextView = view.findViewById(R.id.goalView)
        val time: TextView = view.findViewById(R.id.timeView)
        val habitButton:TextView = view.findViewById(R.id.hButton)

    }
}
