package com.example.roomstevdzafinal2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomstevdzafinal2.data.NoteRoomDB
import com.example.roomstevdzafinal2.models.Note
import com.example.roomstevdzafinal2.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val mLiveData: LiveData<List<Note>>
    val repository: NoteRepository

    init {
        val noteDao = NoteRoomDB.getNoteDatabase(application).getNoteDao()
        repository = NoteRepository(noteDao)
        mLiveData = repository.readAllData
    }

    fun addNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            repository.addNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteNote(note)
        }
    }


}