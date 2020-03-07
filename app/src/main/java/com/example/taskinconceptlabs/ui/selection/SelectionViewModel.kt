package com.example.taskinconceptlabs.ui.selection

import android.app.Application
import androidx.lifecycle.*
import com.example.taskinconceptlabs.data.Repository
import com.example.taskinconceptlabs.data.model.User
import com.example.taskinconceptlabs.ui.OnHolderClickListener
import kotlinx.coroutines.launch

class SelectionViewModel(application: Application) : AndroidViewModel(application),
    OnHolderClickListener {

    private val repository: Repository? by lazy { Repository.getInstance(application) }
    private val _usersFromDb = repository?.getUsersFromDb()

    fun getChosenUsers(): LiveData<List<User>>? = _usersFromDb

    override fun addUser(user: User) {
    }

    override fun remove(id: Int) {
        viewModelScope.launch {
            repository?.removeUser(id)
        }
    }
}