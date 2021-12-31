package com.example.atry

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

fun createNotificationChannel(channelId:String, context: Context){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val name = "RandemanApp"
        val desc = "My channel RandemanApp"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply{
            description = desc
        }
        val notificationManager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


}
@ExperimentalMaterialApi
fun simpleNotificationWithTopAction(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle:String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
){
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context,0,intent,0)
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.logop75)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
    with(NotificationManagerCompat.from(context)){
        notify(notificationId, builder.build())
    }





}

