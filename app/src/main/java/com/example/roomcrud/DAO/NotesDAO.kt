package com.example.roomcrud.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomcrud.Entity.Notes

@Dao
interface NotesDAO {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Delete
    suspend fun delete(notes: Notes)


    @Update
    suspend fun update(notes: Notes)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("DELETE FROM notesTable")
    fun deleteTable()
}