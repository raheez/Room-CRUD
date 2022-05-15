package com.example.roomcrud

import com.example.roomcrud.Entity.Notes

interface notesClickInterface {

    fun removeNotes(notes:Notes)
    fun updateNotes(notes:Notes)
}