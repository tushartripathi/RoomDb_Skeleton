package com.artistecomm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class],version=1,exportSchema=false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao():UserDao

    companion object {
        @Volatile
        private var INSTACE : UserDatabase?=null

        fun getDataBaseInstance(context: Context):UserDatabase
        {
            if(INSTACE != null)
            {
                return INSTACE as UserDatabase
            }
            synchronized(this){
                INSTACE = Room.databaseBuilder(context.applicationContext,
                    UserDatabase::class.java,
                    "user_database").build()
                return INSTACE as UserDatabase
            }
        }
    }

}