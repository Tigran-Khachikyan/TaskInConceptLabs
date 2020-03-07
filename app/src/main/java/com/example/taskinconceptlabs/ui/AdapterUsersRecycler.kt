package com.example.taskinconceptlabs.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskinconceptlabs.R
import com.example.taskinconceptlabs.data.model.User

class AdapterUsersRecycler(
    var users: List<User>?,
    var onHolderClickListener: OnHolderClickListener?
) :
    RecyclerView.Adapter<AdapterUsersRecycler.UserHolder>() {

    inner class UserHolder(
        itemView: View,
        onHolderClickListener: OnHolderClickListener?
    ) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvEmail: TextView = itemView.findViewById(R.id.tv_email)
        val star: ImageView = itemView.findViewById(R.id.iv_star)

        init {
            itemView.setOnClickListener {
                val user = users?.get(adapterPosition)
                user?.let {
                    if (it.selected) onHolderClickListener?.remove(user.id)
                    else onHolderClickListener?.addUser(user)
                    it.selected = !it.selected
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder, parent, false)
        return UserHolder(view, onHolderClickListener)
    }

    override fun getItemCount(): Int = users?.size ?: 0

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val currentUser = users?.get(position)
        holder.tvName.text = currentUser?.name
        holder.tvEmail.text = currentUser?.email
        currentUser?.let {
            holder.star.setSelection(currentUser.selected)
        }
    }

    private fun ImageView.setSelection(selected: Boolean) {
        setImageResource(if (selected) R.drawable.ic_star_chosen else R.drawable.ic_star)
    }

}