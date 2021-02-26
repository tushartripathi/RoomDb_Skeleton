package com.artistecomm.data

import androidx.lifecycle.LiveData

class UserRepository(val userDao : UserDao) {

    val readAllData : LiveData<List<User>> = userDao.readAllData()
    suspend fun addUser(user:User)
    {
        userDao.addUser(user)
    }
}