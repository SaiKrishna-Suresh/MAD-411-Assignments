package com.zybooks.exammock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HabitFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val habitName = it.getString("habitName")?:"Unknown"
            val habitGoal = it.getString("habitGoal")?:"Unknown"
            val habitTime = it.getString("habitTime")?:"Unknown"

            view.findViewById<TextView>(R.id.habitName).text = "Name: $habitName"
            view.findViewById<TextView>(R.id.habitGoal).text="Goal: $habitGoal"
            view.findViewById<TextView>(R.id.habitTime).text="Time: $habitTime"
        }
    }


}