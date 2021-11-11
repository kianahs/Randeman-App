package com.example.atry

import com.mysql.cj.jdbc.CallableStatement
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Types
import java.util.*

fun main() {
    val connectionProps = Properties()
    connectionProps.put("user", "root")
    connectionProps.put("password", "")
    try {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
        var conn = DriverManager.getConnection(
            "jdbc:" + "mysql" + "://" +
                    "127.0.0.1" +
                    ":" + "3306" + "/" +
                    "resource_manager_db",
            connectionProps
        )

        val callableStatement: CallableStatement = conn.prepareCall("{CALL ADD_NEW_RESOURCE(?,?)}") as CallableStatement

        callableStatement.setString(1, "JOHNFORD")
        callableStatement.setString(2, "Nothing")
        callableStatement.executeQuery()





    } catch (ex: SQLException) {
        // handle any errors
        ex.printStackTrace()
    } catch (ex: Exception) {
        // handle any errors
        ex.printStackTrace()
    }
}

class Resource (val resource_name: String, val resource_ID: Int){

    val tasks_list: MutableList<Task> = ArrayList()
    val name = resource_name
    val ID = resource_ID

    fun assign_task_to_resource(task: Task){

        tasks_list.add(task)
    }

    fun sort_tasks_by_priorities(){

        tasks_list.sortByDescending { task -> task.get_task_priority() }

        for(task in tasks_list){
            print(task)
        }


    }




}


class Task (val task_name: String, val task_ID: Int, val task_duration: Int, val task_priority: Int ){

    val name = task_name
    val ID = task_ID
    val duration = task_duration
    val priority = task_priority

    fun get_task_priority(): Int{

        return priority
    }
    fun get_task_name(): String{
        return name
    }

    override fun toString(): String {
        return "Task(task_name='$task_name')"
    }


}

fun start(){

    val a1 = Task("a1", 1, 20,100)
    val a2 = Task("a2", 2, 20,700)
    val a3 = Task("a3", 3, 20,500)

    val CNC = Resource("CNC", 1)
    CNC.assign_task_to_resource(a2)
    CNC.assign_task_to_resource(a1)
    CNC.assign_task_to_resource(a3)

    CNC.sort_tasks_by_priorities()

}