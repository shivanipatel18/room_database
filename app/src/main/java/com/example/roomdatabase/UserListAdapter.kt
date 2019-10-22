package com.example.roomdatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UserListAdapter internal constructor(context: Context) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<User>()

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = users[position]
        holder.userItemView.text = current.first_name +" "+ current.last_name
    }


    internal fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = users.size


}