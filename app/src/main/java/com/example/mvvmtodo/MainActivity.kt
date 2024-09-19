package com.example.mvvmtodo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtodo.ViewModels.MainViewModel
import com.example.mvvmtodo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemTouchHelperCallback: ItemTouchHelper.SimpleCallback

    private val adapter = TasksRecyclerAdapter { task, isDone ->
        mainViewModel.update(task.copy(isDone = isDone))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initItemTouchHelper()
        initRecyclerView()

        mainViewModel.tasks.observe(this) { tasks ->
            adapter.submitList(tasks.toList())
        }

        binding.addTaskFab.setOnClickListener {
            val createTaskBottomSheet = NewTaskBottomSheet()
            createTaskBottomSheet.show(supportFragmentManager, createTaskBottomSheet.tag)
        }
    }

    private fun initRecyclerView() {
        binding.tasksRecycler.layoutManager = LinearLayoutManager(this)
        binding.tasksRecycler.adapter = adapter
    }

    private fun initItemTouchHelper() {
        itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val task = adapter.currentList[viewHolder.adapterPosition]
                mainViewModel.delete(task)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.tasksRecycler)
    }
}
