package com.example.atry.data.remote.dto

class FAQItem(val itemTitle:String, val itemContent:String) {


    fun getTitle(): String{
        return itemTitle
    }

    fun getContent(): String {
        return itemContent
    }

}