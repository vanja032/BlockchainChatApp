package com.example.chatapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIInterface {
    @POST("/getuser")
    fun Login(@Body request: GetUser) : Call<User>
    @POST("/addmessage")
    fun SendMessage(@Body request: AddMessage) : Call<MessageResponse>
}