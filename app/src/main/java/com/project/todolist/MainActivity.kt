package com.project.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.todolist.taskPage.TaskFragment
import com.project.todolist.databinding.ActivityMainBinding
import com.project.todolist.taskPage.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.bottomNavigationView.background = null
//        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        replaceFragments(DashboardFragment())

//        val projectName = findViewById<Button>(R.id.btnAddProject)
//
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
//        projectName.setOnClickListener {
//            NewTaskSheet().show(supportFragmentManager, "NewTaskTag")
//        }
//
//        taskViewModel.name.observe(this){
//            var taskName = findViewById<TextView>(R.id.taskName)
//            taskName.text = String.format("", it)
//        }
//
//        taskViewModel.desc.observe(this){
//            var taskDescription = findViewById<TextView>(R.id.taskDescription)
//            taskDescription.text = String.format("", it)
//        }

        binding.bottomNavigationView.setOnItemSelectedListener {

                when (it.itemId) {
                    R.id.dashboardFragment -> replaceFragments(DashboardFragment())
                    R.id.taskFragment -> replaceFragments(TaskFragment())
                    R.id.notesFragment -> replaceFragments(NotesFragment())
                    R.id.profileFragment -> replaceFragments(ProfileFragment())

                    else -> {
                    }
                }
                true
            }
        }

        private fun replaceFragments(fragment: Fragment) {
            val fragmentManger = supportFragmentManager
            val fragmentTransaction = fragmentManger.beginTransaction()
            fragmentTransaction.replace(R.id.mainContainer, fragment)
            fragmentTransaction.commit()
        }
    }
