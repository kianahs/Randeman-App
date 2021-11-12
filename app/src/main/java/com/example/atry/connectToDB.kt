package com.example.atry

import com.mysql.cj.jdbc.CallableStatement
import java.sql.*
import java.util.*

fun connectToDataBase(): Connection{
    val connectionProps = Properties()
    connectionProps.put("user", "root")
    connectionProps.put("password", "")
    Class.forName("com.mysql.cj.jdbc.Driver").newInstance()

    var conn = DriverManager.getConnection(
        "jdbc:" + "mysql" + "://" +
                "127.0.0.1" +
                ":" + "3306" + "/" +
                "resource_manager_db",
        connectionProps
    )

    return conn

}

fun add_new_resource(conn:Connection, name: String, description: String){

    val new_resource = Resource(name, description)

    val callableStatement: CallableStatement = conn.prepareCall("{CALL ADD_NEW_RESOURCE(?,?)}") as CallableStatement

    callableStatement.setString(1, name)
    callableStatement.setString(2, description)
    callableStatement.executeQuery()

}


fun get_all_resources(): MutableList<Resource>{
    var conn = connectToDataBase()

    val allResources: MutableList<Resource> = ArrayList()

    val cs: CallableStatement = conn.prepareCall("{CALL SHOW_ALL_RESOURCES()}") as CallableStatement
    val resultSet: ResultSet = cs.executeQuery()


    while (resultSet.next()) {

        val new_resource = Resource(resultSet.getString(2),
            resultSet.getString(3))

        allResources.add(new_resource)

    }
    conn.close()
    return allResources

}


fun main(){


//    print(get_all_resources(connectToDataBase()))
//
//
//
//    val a1 = Task("a1", 1, 20,100)
//    val a2 = Task("a2", 2, 20,700)
//    val a3 = Task("a3", 3, 20,500)
//
//    val CNC = Resource("CNC", )
//    CNC.assign_task_to_resource(a2)
//    CNC.assign_task_to_resource(a1)
//    CNC.assign_task_to_resource(a3)
//
//    CNC.sort_tasks_by_priorities()

}