package com.example.mvvmtodo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtodo.data.database.Task
import com.example.mvvmtodo.databinding.TaskCardBinding

class TasksRecyclerAdapter(private val onCheckClick: (Task, Boolean) -> Unit): ListAdapter<Task, TasksRecyclerAdapter.MyViewHolder>(TasksDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = TaskCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, onCheckClick)
    }

    class MyViewHolder(val binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, onCheckClick: (Task, Boolean) -> Unit) {
            binding.taskTitle.text = task.title
            binding.taskDescription.text = task.description

            binding.doneCheckbox.setOnCheckedChangeListener(null)

            binding.doneCheckbox.isChecked = task.isDone

            binding.doneCheckbox.setOnCheckedChangeListener { _, isChecked ->
                onCheckClick(task, isChecked)
            }
        }
    }

    class TasksDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}
