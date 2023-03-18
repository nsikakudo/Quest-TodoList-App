package com.project.todolist.taskPage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.todolist.TaskItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(): ViewModel() {

    var taskItems = MutableLiveData<MutableList<TaskItem>?>()

    init {
        taskItems.value = mutableListOf()
    }

    fun addTaskItem(newTask : TaskItem){
        val list = taskItems.value
        list!!.add(newTask)
        taskItems.postValue(list)
    }

    fun updateTaskItem(id: UUID, name: String, desc: String, dueTime: LocalTime?){
        val list = taskItems.value
        val task = list!!.find { it.id == id }!!
        task.name = name
        task.desc = desc
        task.dueTime = dueTime
        taskItems.postValue(list)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCompleted(taskItem : TaskItem){
        val list = taskItems.value
        val task = list!!.find { it.id == taskItem.id }!!
        if (task.completedDate == null)
            task.completedDate = LocalDate.now()
        taskItems.postValue(list)
    }




}












//    fun setTask(newText : String){
//        _nameProject.value = newText
//        _descValue.value = newText
//    }

//    fun setTask() {
//        _nameProject.value.toString()
//        _descValue.value.toString()
//    }