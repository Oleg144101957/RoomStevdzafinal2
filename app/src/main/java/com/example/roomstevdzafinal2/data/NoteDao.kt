package com.example.roomstevdzafinal2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.roomstevdzafinal2.TABLE_NOTE_NAME
import com.example.roomstevdzafinal2.models.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM $TABLE_NOTE_NAME")
    fun readAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)


}