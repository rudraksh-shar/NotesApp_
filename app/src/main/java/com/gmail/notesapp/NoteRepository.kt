package com.gmail.notesapp

import androidx.lifecycle.LiveData


class NoteRepository(private val notedao: NoteDao) {

    // Room executes all queries on a separate thread.
    // Livedata will notify the observer when the data has changed.
    val allNotes: LiveData<List<Note>> = notedao.getAllNotes()

    //now we have implement insert function
    suspend fun insert(note:Note){
        notedao.insert(note)
    }
    //now we have implement delete function
    suspend fun delete(note:Note){
        notedao.delete(note)
    }

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.

}