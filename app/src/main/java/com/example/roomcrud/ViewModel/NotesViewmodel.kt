package com.example.roomcrud.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.example.roomcrud.DAO.NotesDAO
import com.example.roomcrud.Database.NotesDatabase
import com.example.roomcrud.Entity.Notes
import com.example.roomcrud.Repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotesViewmodel(application: Application) :
    AndroidViewModel(application) {

    val mRepository: NotesRepository
    val mAllNotes : LiveData<List<Notes>>
    init {
        val dao =NotesDatabase?.getInstance(application).getNotesDao()
        mRepository = NotesRepository(dao)
        mAllNotes = mRepository.allNotes
    }


    fun addNote(note:Notes){
        viewModelScope?.launch(Dispatchers.IO) {
            mRepository.insert(note)
        }
    }


    fun deleteNote(note: Notes){
        viewModelScope?.launch ( Dispatchers.IO ){
            mRepository?.delete(note)
        }
    }

    fun deleteNotes(){
        viewModelScope?.launch(Dispatchers.IO) {
            mRepository?.deleteTable()
        }
    }


    fun updateNotes(note: Notes){
        viewModelScope?.launch (Dispatchers.IO){
            mRepository?.update(note)
        }
    }
}