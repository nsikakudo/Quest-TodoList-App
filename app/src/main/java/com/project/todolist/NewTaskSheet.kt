package com.project.todolist

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.todolist.taskPage.TaskViewModel
import com.project.todolist.databinding.FragmentNewTaskSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskSheet(var taskItem: TaskItem?): BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding

//        private lateinit var taskViewModel: TaskViewModel
    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (taskItem != null){
            binding.taskTitle.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            binding.addNameTask.text = editable.newEditable(taskItem!!.name)
            binding.addDescription.text = editable.newEditable(taskItem!!.desc)
        }
        else{
            binding.taskTitle.text = "New Task"
        }

        binding.btnSaveProject.setOnClickListener {
            saveAction()
    }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction() {
        val name = binding.addNameTask.text.toString()
        val desc = binding.addDescription.text.toString()
        if (taskItem == null){
            val newTask = TaskItem(name, desc, null, null)
            taskViewModel.addTaskItem(newTask)
        }else{
            taskViewModel.updateTaskItem(taskItem!!.id, name, desc, null)
        }
        binding.addNameTask.text.toString()
        binding.addDescription.text.toString()
        dismiss()
    }
}