package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ConnectionAdapter(private val connections: MutableList<Connection>?, private val context: Context, var clickListener: onItemClickListener)
    : RecyclerView.Adapter<ConnectionAdapter.ViewHolder>() {

    //private lateinit var clickListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        clickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.connection_container, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(connections?.get(position)?.picture).into(holder.Picture)
        holder.Username.text = connections?.get(position)?.username
        holder.Name.text = connections?.get(position)?.f_name + " " + connections?.get(position)?.l_name
    }

    override fun getItemCount(): Int {
        return connections?.size ?: 0
    }
    class ViewHolder (val view: View, listener: onItemClickListener)
        : RecyclerView.ViewHolder(view){
            val Picture: ImageView = view.findViewById(R.id.profilepicture)
            val Username: TextView = view.findViewById(R.id.username)
            val Name: TextView = view.findViewById(R.id.name)

            init {
                view.setOnClickListener{
                    listener.onItemClick(adapterPosition)
                }
            }
        }
}
