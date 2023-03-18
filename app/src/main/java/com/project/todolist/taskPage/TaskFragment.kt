package com.project.todolist.taskPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import com.project.todolist.NewTaskSheet
import com.project.todolist.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment: Fragment() {

    private lateinit var binding: FragmentTaskBinding

//    private val taskViewModel: TaskViewModel by viewModels()
    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        //        return inflater.inflate(R.layout.fragment_task, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//       taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]


        binding.btnAddProject.setOnClickListener {
            NewTaskSheet(null).show(childFragmentManager, "")

        }

//        taskViewModel.name.observe(viewLifecycleOwner){
//            binding.taskName.text = String.format("%s", it)
//        }
//
//        taskViewModel.desc.observe(viewLifecycleOwner){
//            binding.taskDesc.text = String.format("%s", it)
//        }

    }
}