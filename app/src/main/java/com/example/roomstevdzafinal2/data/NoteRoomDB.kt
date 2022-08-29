package com.example.roomstevdzafinal2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomstevdzafinal2.DB_NOTE_NAME
import com.example.roomstevdzafinal2.models.Note


@Database(entities = [Note::class], exportSchema = true, version = 1)
abstract class NoteRoomDB : RoomDatabase(){

    abstract fun getNoteDao(): NoteDao

    companion object{

        @Volatile
        var INSTANCE: NoteRoomDB? = null

        fun getNoteDatabase(context: Context) : NoteRoomDB {

            val temp = INSTANCE
            if (temp != null) return temp

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDB::class.java,
                    "$DB_NOTE_NAME"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}