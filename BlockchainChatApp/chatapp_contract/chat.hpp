//chat.hpp
#pragma once
#include <ctime>
#include <string.h>
#include <eosio/eosio.hpp>

using namespace eosio;
using namespace std;

class [[eosio::contract]] chat : public eosio::contract{
    public:
        chat(name receiver, name code, datastream<const char*> ds): contract(receiver, code, ds),
        _users(receiver, receiver.value)
        {}

        struct Message{
            uint64_t    subject;
            string      status;
            name        username;
            string      f_name;
            string      l_name;
            string      picture;
            string      message;
            time_t      time;
            //string      stime;
        };
        typedef struct Message Message;

        struct Connection{
            uint64_t    connection;
            name        username;
            string      f_name;
            string      l_name;
            string      picture;
            time_t      time;
            //string      stime;
        };
        typedef struct Connection Connection;

        struct [[eosio::table]] users{
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
        };
        typedef eosio::multi_index<"users"_n, users, eosio::indexed_by<"username"_n, eosio::const_mem_fun<users, uint64_t, &users::by_username>>> users_t;
        users_t _users;

        
        [[eosio::action]] void addmessage(uint64_t from, uint64_t to, string message, name username, string password);
        [[eosio::action]] void adduser(name username, string password, string email, string f_name, string l_name, string picture);
        [[eosio::action]] void addconnect(uint64_t id, uint64_t connection, name username, string password);
        [[eosio::action]] void removeuser(uint64_t id, name username, string password);
        //[[eosio::action]] void getuser(name username, string password);

        using addmessage_action = action_wrapper<"addmessage"_n, &chat::addmessage>;
        using adduser_action = action_wrapper<"adduser"_n, &chat::adduser>;
        using addconnect_action = action_wrapper<"addconnect"_n, &chat::addconnect>;
        using removeuser_action = action_wrapper<"removeuser"_n, &chat::removeuser>;
        //using getuser_action = action_wrapper<"getuser"_n, &chat::getuser>;
};