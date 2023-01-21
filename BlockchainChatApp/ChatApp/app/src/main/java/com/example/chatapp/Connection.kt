package com.example.chatapp

/*cstruct Connection{
    uint64_t    connection;
    name        username;
    string      f_name;
    string      l_name;
    string      picture;
    time_t      time;
}; */
data class Connection (
    var connection : String? = null,
    var username : String? = null,
    var f_name : String? = null,
    var l_name : String? = null,
    var picture : String? = null,
    var time : String? = null
)