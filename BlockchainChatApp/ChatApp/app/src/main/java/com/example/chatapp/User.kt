package com.example.chatapp

/* struct [[eosio::table]] users{
    uint64_t            user_id;
    name                username;
    string              password;
    string              email;
    string              f_name;
    string              l_name;
    string              picture;
    vector< Connection >  connections;
    vector< Message >   messages;

    uint64_t    primary_key() const { return user_id; }
    uint64_t      by_username() const { return username.value; }
    EOSLIB_SERIALIZE(users, (user_id)(username)(password)(email)(f_name)(l_name)(picture)(connections)(messages))
}; */

data class User(
    var user_id : String? = null,
    var username : String? = null,
    var password : String? = null,
    var email : String? = null,
    var f_name : String? = null,
    var l_name : String? = null,
    var picture : String? = null,
    var messages : MutableList<Message>? = mutableListOf<Message>(),
    var connections : MutableList<Connection>? = mutableListOf<Connection>()
)