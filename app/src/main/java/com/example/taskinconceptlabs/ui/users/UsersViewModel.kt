package com.example.taskinconceptlabs.ui.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskinconceptlabs.data.Repository
import com.example.taskinconceptlabs.data.model.User
import com.example.taskinconceptlabs.ui.OnHolderClickListener
import kotlinx.coroutines.launch

class UsersViewModel(application: Application) : AndroidViewModel(application),
    OnHolderClickListener {

    private val repository: Repository? by lazy { Repository.getInstance(application) }

    private val _usersFromApi = repository?.getUsersFromApi()
    private val _usersFromDb = repository?.getUsersFromDb()
    private val _mediator = MediatorLiveData<List<User>>()


    fun getUsers(): LiveData<List<User>?> {
        viewModelScope.launch {
            _usersFromApi?.let {
                _mediator.removeSource(_usersFromApi)
                _mediator.addSource(_usersFromApi) {
                    _mediator.value =
                        combine(_usersFromApi, _usersFromDb)
                }
            }
            _usersFromDb?.let {
                _mediator.removeSource(_usersFromDb)
                _mediator.addSource(_usersFromDb) {
                    _mediator.value = combine(_usersFromApi, _usersFromDb)
                }
            }
        }
        return _mediator
    }

    private fun combine(
        _fromApi: LiveData<List<User>>?, _fromDb: LiveData<List<User>>?
    ): List<User>? {

        val usersFromApi = _fromApi?.value
        val usersFromDb = _fromDb?.value

        usersFromApi?.let { api ->
            usersFromDb?.let { db ->
                db.forEach { userDB ->
                    for (i in api.indices)
                        if (userDB.id == api[i].id) {
                            api[i].selected = true
                            break
                        }
                }
            }
        }
        return usersFromApi
    }

    override fun addUser(user: User) {
        viewModelScope.launch {
            repository?.insertUser(user)
        }
    }

    override fun remove(id: Int) {
        viewModelScope.launch {
            repository?.removeUser(id)
        }
    }
}