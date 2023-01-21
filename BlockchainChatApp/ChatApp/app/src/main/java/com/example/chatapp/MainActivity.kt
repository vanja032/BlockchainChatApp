package com.example.chatapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import com.example.chatapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request_api = ServiceBuilder.buildService(APIInterface::class.java)

        binding.btnLogin.setOnClickListener {
            val username : String = binding.tbUsername.text.toString()
            val password : String = binding.tbPassword.text.toString()

            if(username.isNotEmpty() and password.isNotEmpty()){
                hideKeyboard()
                request_api.Login(GetUser(username, password)).enqueue(
                    object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if(response.code() == 200) {
                                DataContainer.user.user_id = response.body()?.user_id
                                DataContainer.user.username = response.body()?.username
                                DataContainer.user.password = response.body()?.password
                                DataContainer.user.email = response.body()?.email
                                DataContainer.user.f_name = response.body()?.f_name
                                DataContainer.user.l_name = response.body()?.l_name
                                DataContainer.user.picture = response.body()?.picture
                                DataContainer.user.messages = response.body()?.messages
                                DataContainer.user.connections = response.body()?.connections
                                Log.d("TAG", "Successfully logged on")
                                Toast.makeText(applicationContext, "Successfully logged on", Toast.LENGTH_LONG).show()
                                binding.tbUsername.text.clear()
                                binding.tbPassword.text.clear()
                                Thread.sleep(1000)
                                val intent = Intent(applicationContext, Dashboard::class.java)
                                startActivity(intent)
                            }
                            else {
                                Log.d("TAG", "${response.message().toString()}")
                                Toast.makeText(applicationContext, "${response.message().toString()}", Toast.LENGTH_LONG).show()
                                binding.tbUsername.text.clear()
                                binding.tbPassword.text.clear()
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Log.d("TAG", "${t.toString()}")
                            Toast.makeText(applicationContext, "Error occurred while logging in the user.", Toast.LENGTH_LONG).show()
                        }

                    }
                )
            }
            else{
                Toast.makeText(this, "Username and password must not be empty", Toast.LENGTH_LONG).show()
            }
        }
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