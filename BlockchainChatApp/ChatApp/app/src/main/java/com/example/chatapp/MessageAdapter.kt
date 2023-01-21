package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.jar.Attributes.Name

class MessageAdapter(private val messages: MutableList<Message>?, private val context: Context)
    : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    /*private lateinit var clickListener: onItemClickListener*/

    /*interface onItemClickListener{
        fun onItemClick(position: Int)
    }*/
    /*fun setOnItemClickListener(listener: onItemClickListener){
        clickListener = listener
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_container, parent, false)
        return ViewHolder(view/*, clickListener*/)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*Glide.with(context).load(connections?.get(position)?.picture).into(holder.Picture)
        holder.Username.text = connections?.get(position)?.username
        holder.Name.text = connections?.get(position)?.f_name + " " + connections?.get(position)?.l_name*/
        if(messages?.get(position)?.status == "sent"){
            holder.Name.text = "Me:"
        }
        else if(messages?.get(position)?.status == "received"){
            holder.Name.text = messages?.get(position)?.f_name + ":"
        }
        holder.Message.text = messages?.get(position)?.message
    }

    override fun getItemCount(): Int {
        return messages?.size ?: 0
    }
    class ViewHolder (val view: View/*, listener: onItemClickListener*/)
        : RecyclerView.ViewHolder(view){
        val Name: TextView = view.findViewById(R.id.sendername)
        val Message: TextView = view.findViewById(R.id.messagetext)

        /*init {
            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }*/
    }
}