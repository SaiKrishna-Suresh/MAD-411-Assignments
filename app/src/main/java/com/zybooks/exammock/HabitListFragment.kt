package com.zybooks.exammock

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

private const val FILE_NAME = "habits.txt"
class HabitListFragment : Fragment() {

    private lateinit var habitName: EditText
    private lateinit var habitGoal: EditText
    private lateinit var habitTime: EditText
    private lateinit var habitButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var loadDefaultsButton: Button
    private lateinit var showQuoteButton:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_habit_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("ActivityLifeCycle","created")
        habitName = view.findViewById(R.id.name)
        habitGoal = view.findViewById(R.id.goal)
        habitTime = view.findViewById(R.id.time)
        habitButton = view.findViewById(R.id.button)
        recyclerView = view.findViewById(R.id.recycler)
        loadDefaultsButton = view.findViewById(R.id.loadDefaultsButton)
        showQuoteButton = view.findViewById(R.id.showQuote)

        val habitList = loadTasksFromFile(requireContext())

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = HabitAdapter(habitList,findNavController())
        recyclerView.adapter = adapter

        loadDefaultsButton.setOnClickListener {
            val defaultHabits = listOf(
                Habit("Drink Water", "8 Glasses a Day", "7:00 AM"),
                Habit("Morning Walk", "30 Minutes", "6:30 AM"),
                Habit("Read Book", "20 Pages", "9:00 PM")
            )
            val startIndex = habitList.size
            habitList.addAll(defaultHabits)
            adapter.notifyItemRangeInserted(startIndex, defaultHabits.size)
            saveTasksToFile(requireContext(),habitList)

        }
        showQuoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_habitListFragment_to_quoteFragment)

        }

        habitButton.setOnClickListener {
            val name = habitName.text.toString()
            val goal = habitGoal.text.toString()
            val time = habitTime.text.toString()

            val habit = Habit(name,goal,time)
            habitList.add(habit)
            adapter.notifyItemChanged(habitList.size)

            saveTasksToFile(requireContext(),habitList)

            habitName.text.clear()
            habitGoal.text.clear()
            habitTime.text.clear()

        }


    }


    private fun saveTasksToFile(context:Context,habitList: List<Habit>){
        try {
            val json = Gson().toJson(habitList)
            context.openFileOutput(FILE_NAME,Context.MODE_PRIVATE).use{output->
                output.write(json.toByteArray())
            }
            Log.d("FileStorage","Habits saved successfully")
        }catch (e:IOException){
            Log.d("FileStorage","Error saving Habits")
        }
    }

    private fun loadTasksFromFile(context: Context):MutableList <Habit>{
        val habitList: MutableList<Habit> = mutableListOf()
        try {
            val file = File(context.filesDir, FILE_NAME)
            if(!file.exists()) return habitList

            val json = file.readText()
            val type = object: TypeToken<List<Habit>>(){}.type
            val loadedTasks: List<Habit> = Gson().fromJson(json,type)
            habitList.addAll(loadedTasks)

            Log.d("FileStorage","Tasks loaded successfully")
        }catch (e: FileNotFoundException){
            Log.e("FileStorage","File not found: ${e.message}")

        }
        return habitList

    }





    override fun onStart() {
        super.onStart()
        Log.d("ActivityLifecycle","OnStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ActivityLifeCycle","OnResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ActivityLifeCycle","OnPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ActivityLifeCycle","OnStop Called")
    }

}




