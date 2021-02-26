package com.artistecomm.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artistecomm.data.User
import com.artistecomm.data.UserDatabase
import com.artistecomm.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var readAllData: LiveData<List<User>>
    private lateinit var repository: UserRepository

    init {
        val userDao = UserDatabase.getDataBaseInstance(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUserToDb(user:User)
    {
        viewModelScope.launch (Dispatchers.IO)
        {
            repository.addUser(user)
        }

    }
}