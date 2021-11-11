package com.example.atry

import java.util.ArrayList

class Resource (val resource_name: String, val resource_description: String){

    val tasks_list: MutableList<Task> = ArrayList()
    val name = resource_name
    val description = resource_description

    fun assign_task_to_resource(task: Task){

        tasks_list.add(task)
    }

    fun sort_tasks_by_priorities(){

        tasks_list.sortByDescending { task -> task.get_task_priority() }

        for(task in tasks_list){
            print(task)
        }


    }

    override fun toString(): String {
        return "Resource(name='$name', description='$description')"
    }


}