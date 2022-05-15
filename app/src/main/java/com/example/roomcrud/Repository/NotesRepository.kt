package com.example.roomcrud.Repository

import androidx.lifecycle.LiveData
import com.example.roomcrud.DAO.NotesDAO
import com.example.roomcrud.Entity.Notes

class NotesRepository(private val notesDao:NotesDAO) {


    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insert(note:Notes){
        notesDao.insert(note)
    }

    suspend fun delete(note: Notes){
        notesDao.delete(note)
    }

    suspend fun deleteTable(){
        notesDao.deleteTable()
    }

    suspend fun update(note: Notes){
        notesDao.update(note)
    }


}