package com.example.mvvmtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmtodo.ViewModels.MainViewModel
import com.example.mvvmtodo.data.database.Task
import com.example.mvvmtodo.databinding.TaskFormBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewTaskBottomSheet : BottomSheetDialogFragment() {
    private lateinit var _binding: TaskFormBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = TaskFormBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        _binding.createTaskButton.setOnClickListener {
            val title = _binding.titleEdit.editText?.text.toString()
            val description = _binding.descriptionEdit.editText?.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty())  {
                mainViewModel.addTask(Task(title = title, description = description, isDone = false))
                dismiss()
            }
        }
    }

}
