package com.example.chatapp

object DataContainer {
    val user : User = User()
    var current_chat : Connection? = null
    var current_messages : MutableList<Message>? = mutableListOf<Message>()
}