package com.example.chatapp

//{"from": "0", "to": "1", "message": "Some text.", "username": "user", "password": "user123"}
data class AddMessage (
    var from : String? = null,
    var to : String? = null,
    var message : String? = null,
    var username : String? = null,
    var password : String? = null
)