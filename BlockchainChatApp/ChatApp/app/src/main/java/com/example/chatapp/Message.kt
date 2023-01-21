package com.example.chatapp

/* struct Message{
    uint64_t    subject;
    string      status;
    name        username;
    string      f_name;
    string      l_name;
    string      picture;
    string      message;
    time_t      time;
}; */
data class Message (
    var subject : String? = null,
    var status : String? = null,
    var username : String? = null,
    var f_name : String? = null,
    var l_name : String? = null,
    var picture : String? = null,
    var message : String? = null,
    var time : String? = null
)