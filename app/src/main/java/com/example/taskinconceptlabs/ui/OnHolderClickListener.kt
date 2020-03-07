package com.example.taskinconceptlabs.ui

import com.example.taskinconceptlabs.data.model.User

interface OnHolderClickListener {
    fun addUser(user: User)
    fun remove(id: Int)
}