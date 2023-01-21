package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBindings
import com.example.chatapp.databinding.ActivityDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DataContainer.current_chat = null;
        if(DataContainer.user == null){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        val request_api = ServiceBuilder.buildService(APIInterface::class.java)

        request_api.Login(GetUser(DataContainer.user.username, DataContainer.user.password)).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.code() == 200) {
                        DataContainer.user.messages = response.body()?.messages
                        DataContainer.user.connections = response.body()?.connections
                        /*binding.connections.apply {
                            layoutManager = LinearLayoutManager(applicationContext)
                            adapter = ConnectionAdapter(DataContainer.user.connections, applicationContext)
                        }*/
                    }
                    else {
                        Log.d("TAG", "${response.message().toString()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("TAG", "${t.toString()}")
                }

            }
        )
        binding.userName.text = DataContainer.user.f_name + " " + DataContainer.user.l_name
        /*binding.connections.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = ConnectionAdapter(DataContainer.user.connections, applicationContext)
        }*/
        var adapter = ConnectionAdapter(DataContainer.user.connections, applicationContext,object : ConnectionAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                DataContainer.current_chat = DataContainer.user.connections?.get(position)
                val intent = Intent(applicationContext, ChatActivity::class.java)
                startActivity(intent)
            }
        })
        binding.connections.layoutManager = LinearLayoutManager(applicationContext)
        binding.connections.adapter = adapter
        /*adapter.setOnItemClickListener(object : ConnectionAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                DataContainer.current_chat = DataContainer.user.connections?.get(position)
                val intent = Intent(applicationContext, ChatActivity::class.java)
                startActivity(intent)
            }
        })*/

        binding.signOut.setOnClickListener {
            DataContainer.user.user_id = null
            DataContainer.user.username = null
            DataContainer.user.password = null
            DataContainer.user.email = null
            DataContainer.user.f_name = null
            DataContainer.user.l_name = null
            DataContainer.user.picture = null
            DataContainer.user.messages = null
            DataContainer.user.connections = null
            DataContainer.current_chat = null
            Toast.makeText(applicationContext, "Successfully logged out.", Toast.LENGTH_LONG).show()
            Thread.sleep(1000)
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}