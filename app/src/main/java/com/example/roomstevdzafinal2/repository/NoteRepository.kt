package com.example.roomstevdzafinal2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomstevdzafinal2.data.NoteDao
import com.example.roomstevdzafinal2.models.Note

class NoteRepository(val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.readAllNotes()

    fun addNote(note: Note){
        noteDao.addNote(note)
    }

    fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

    fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

}