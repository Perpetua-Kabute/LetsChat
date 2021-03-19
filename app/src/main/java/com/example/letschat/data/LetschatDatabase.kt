package com.example.letschat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.letschat.data.dao.DeviceDatabaseDao
import com.example.letschat.data.entities.Device

@Database(entities = [Device::class], version= 1, exportSchema = false )
abstract class LetschatDatabase: RoomDatabase(){
    //declare an abstract value of DeviceDatabaseDao
    abstract fun deviceDatabaseDao(): DeviceDatabaseDao

    //declare a companion object
    companion object{

        /**declare a @volatile INSTANCE variable
        INSTANCE will keep a reference to the database meaning we don't repeatedly open connections to the db which is expensive
        changes made to one thread of INSTANCE are visible to all threads
        **/
        @Volatile
        private var INSTANCE: LetschatDatabase? = null

        //define getinstance method with a synchronised block

        fun getInstance(context: Context): LetschatDatabase {
            //synchronised ensures that when multiple threads ask for access only one thread can enter the block of code ensuring the db gets initialized once
            synchronized(this){
                var instance = INSTANCE

                /**check if database already exists
                if not use Room.DatabaseBuilder to create it
                **/
                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            LetschatDatabase::class.java,
                            "lets_chat_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }

    /**like a todo
     * In a real app, you should consider setting a directory for Room to use to export the schema so you can check the current schema into your version control system.
     */


}