//chat.cpp
#include "chat.hpp"

void chat::adduser(name username, string password, string email, string f_name, string l_name, string picture){
    require_auth("chatapp"_n);
    auto usr_it = _users.get_index<"username"_n>();
    for(auto it = usr_it.begin(); it != usr_it.end(); it++){
        if(it->username == username)
            return;
    }
    _users.emplace(get_self(), [&](auto& usr){
        usr.user_id = _users.available_primary_key();
        usr.username = username;
        usr.password = password;
        usr.email = email;
        usr.f_name = f_name;
        usr.l_name = l_name;
        usr.picture = picture;
    });
}

void chat::addmessage(uint64_t from, uint64_t to, string message, name username, string password){
    require_auth("chatapp"_n);
    //Not necessary, because we need to have a connection to send a mssage, so we cannot send the message to ourself
    //check(from != to, "Cannot connect to yourself.");
    auto usr_it_f = _users.find(from);
    check(usr_it_f != _users.end(), "Unknown sender user.");
    auto usr_it_t = _users.find(to);
    check(usr_it_t != _users.end(), "Unknown receiver user.");
    if(usr_it_f->username == username && usr_it_f->password.compare(password) == 0){
        for(auto it = usr_it_f->connections.begin(); it != usr_it_f->connections.end(); it++){
            if(it->connection == to){
                time_t currtime = std::time(0);
                //char* dt = std::ctime(&currtime);
                _users.modify(usr_it_t, get_self(), [&](auto& usr){
                    usr.messages.push_back(Message{
                        .subject = from,
                        .status = "received",
                        .username = usr_it_f->username,
                        .f_name = usr_it_f->f_name,
                        .l_name = usr_it_f->l_name,
                        .picture = usr_it_f->picture,
                        .message = message, 
                        .time = currtime
                        //.stime = dt
                        }
                    );
                });
                _users.modify(usr_it_f, get_self(), [&](auto& usr){
                    usr.messages.push_back(Message{
                        .subject = to,
                        .status = "sent",
                        .username = usr_it_t->username,
                        .f_name = usr_it_t->f_name,
                        .l_name = usr_it_t->l_name,
                        .picture = usr_it_t->picture,
                        .message = message, 
                        .time = currtime
                        //.stime = dt
                        }
                    );
                });
                break;
            }
        }
    }
    
}

void chat::addconnect(uint64_t id, uint64_t connection, name username, string password){
    require_auth("chatapp"_n);
    check(id != connection, "Cannot connect to yourself.");
    auto usr_it_f = _users.find(id);
    check(usr_it_f != _users.end(), "Unknown connection user.");
    auto usr_it_t = _users.find(connection);
    check(usr_it_t != _users.end(), "Unknown connection user.");
    for(auto it = usr_it_f->connections.begin(); it != usr_it_f->connections.end(); it++){
        if(it->connection == connection)
            return;
    }
    if(usr_it_f->username == username && usr_it_f->password.compare(password) == 0){
        time_t currtime = time(0);
        //char* dt = ctime(&currtime);
        _users.modify(usr_it_f, get_self(), [&](auto& usr){
            usr.connections.push_back(Connection{
                        .connection = connection,
                        .username = usr_it_t->username,
                        .f_name = usr_it_t->f_name,
                        .l_name = usr_it_t->l_name,
                        .picture = usr_it_t->picture,
                        .time = currtime
                        //.stime = dt
                        }
                    );
        });
    }
}

void chat::removeuser(uint64_t id, name username, string password){
    require_auth("chatapp"_n);
    auto usr_it = _users.find(id);
    if(usr_it->username == username && usr_it->password.compare(password) == 0){
        if(usr_it != _users.end())
            _users.erase(usr_it);
    }
}

/*void chat::getuser(name username, string password){
    require_auth("chatapp"_n);
    auto usr_it = _users.get_index<"username"_n>();
    for(auto it = usr_it.begin(); it != usr_it.end(); it++){
        if(it->username == username && it->password.compare(password) == 0){
            string response = "{\"username\":\"";
            response += it->username.to_string();
            print_f("{%}", response);
        }
    }
}*/

EOSIO_DISPATCH( chat, (addmessage)(adduser)(addconnect)(removeuser))