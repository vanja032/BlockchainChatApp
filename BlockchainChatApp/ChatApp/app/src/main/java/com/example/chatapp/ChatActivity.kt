package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.databinding.ActivityChatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(DataContainer.user == null){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        else if(DataContainer.current_chat == null){
            val intent = Intent(applicationContext, Dashboard::class.java)
            startActivity(intent)
        }
        binding.userName.text = DataContainer.current_chat?.f_name + " " + DataContainer.current_chat?.l_name
        val messages : List<Message>? =  DataContainer.user.messages?.filter{ x -> x.subject == DataContainer.current_chat?.connection }
        DataContainer.current_messages = messages?.toMutableList()
        //Log.d("TAG", "${DataContainer.current_chat?.username}")
        binding.messages.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = MessageAdapter(DataContainer.current_messages, applicationContext)
        }
        binding.messages.scrollToPosition((binding.messages.adapter?.itemCount ?: 1) - 1)

        val request_api = ServiceBuilder.buildService(APIInterface::class.java)

        binding.sendmessage.setOnClickListener {
            val message : String = binding.messageinput.text.toString()

            if(message.isNotEmpty()){
                hideKeyboard()
                request_api.SendMessage(AddMessage(DataContainer.user.user_id,
                    DataContainer.current_chat?.connection, message, DataContainer.user.username, DataContainer.user.password)).enqueue(
                    object : Callback<MessageResponse> {
                        override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                            if(response.code() == 200 && response.body()?.message == "successful") {
                                //Refresh messages
                                binding.messageinput.text.clear()
                                /*Thread.sleep(1000)
                                val intent = Intent(applicationContext, Dashboard::class.java)
                                startActivity(intent)*/
                            }
                            else {
                                Log.d("TAG", "${response.message().toString()}")
                            }
                        }

                        override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                            Log.d("TAG", "${t.toString()}")
                            Toast.makeText(applicationContext, "Error occurred while sending the message to the user.", Toast.LENGTH_LONG).show()
                        }

                    }
                )
            }
            else{
                Toast.makeText(this, "Message text must not be empty", Toast.LENGTH_LONG).show()
            }
        }

        binding.backbutton.setOnClickListener {
            val intent = Intent(applicationContext, Dashboard::class.java)
            startActivity(intent)
        }

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

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                //Call your function here
                RefreshMessages()
                handler.postDelayed(this, 1200)//1.2 sec delay
            }
        }, 0)
    }

    private fun RefreshMessages(){
        val request_api = ServiceBuilder.buildService(APIInterface::class.java)

        request_api.Login(GetUser(DataContainer.user.username, DataContainer.user.password)).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.code() == 200) {
                        if(response.body()?.messages != DataContainer.user.messages) {
                            val messages: List<Message>? =
                                response.body()?.messages?.filter { x -> x.subject == DataContainer.current_chat?.connection && !DataContainer.user.messages!!.contains(x)}
                            val start : Int = DataContainer.user.messages?.size ?: 0
                            DataContainer.user.messages = response.body()?.messages
                            DataContainer.user.connections = response.body()?.connections
                            //DataContainer.current_messages = messages?.toMutableList()
                            messages?.toMutableList()?.let { DataContainer.current_messages?.addAll(it) }
                            val end : Int = DataContainer.user.messages?.size?.minus(start + 1) ?: 0
                            //binding.messages.adapter?.notifyDataSetChanged()
                            // Save state
                            var recyclerViewState : Parcelable =
                                binding.messages!!.layoutManager?.onSaveInstanceState()!!

                            binding.messages.adapter?.notifyItemRangeInserted(start, end)

                            // Restore state
                            binding.messages.layoutManager?.onRestoreInstanceState(recyclerViewState)
                        }
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
    }

    private fun hideKeyboard(){
        val view: View? = this.currentFocus

        // on below line checking if view is not null.
        if (view != null) {
            // on below line we are creating a variable
            // for input manager and initializing it.
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

            // on below line hiding our keyboard.
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
    }
}