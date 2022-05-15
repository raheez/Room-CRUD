package com.example.roomcrud.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomcrud.DAO.NotesDAO
import com.example.roomcrud.Entity.Notes


@Database(entities = arrayOf(Notes::class),version = 1,exportSchema = false)
abstract class NotesDatabase :RoomDatabase() {

    abstract fun getNotesDao():NotesDAO

    companion object{

        @Volatile
        private var mInstance : NotesDatabase?=null

        fun getInstance(context: Context):NotesDatabase{

            if (mInstance!=null){
                return mInstance!!
            }else
            {
                synchronized(this){
                    val mDatabase = Room.databaseBuilder(context.applicationContext,
                    NotesDatabase::class.java,
                    "note_database").build()
                    mInstance = mDatabase

                    return mDatabase

                }
            }
        }
    }

}