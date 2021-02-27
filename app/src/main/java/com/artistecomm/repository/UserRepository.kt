package com.artistecomm.repository

import androidx.lifecycle.LiveData
import com.artistecomm.data.UserDao
import com.artistecomm.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user:User){
        userDao.updateUser(user)
    }

}