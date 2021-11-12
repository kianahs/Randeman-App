package com.example.atry

class Task (val task_name: String,  val task_duration: Int, val task_priority: Int, val task_resource:Resource ){

    val name = task_name
    val duration = task_duration
    val priority = task_priority
    val resource = task_resource

    fun get_task_priority(): Int{

        return priority
    }

    fun get_task_resource(): Resource{

        return resource
    }

    fun get_task_name(): String{
        return name
    }

    override fun toString(): String {
        return "Task(task_name='$task_name')"
    }


}