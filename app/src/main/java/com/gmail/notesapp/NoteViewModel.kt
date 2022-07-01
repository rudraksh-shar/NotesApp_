package com.gmail.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

 class NoteViewModel(application: Application) : AndroidViewModel(application){

      private val repository:NoteRepository
      val allNotes :LiveData<List<Note>>

     //init is the secondary constructor
    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
         repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    //because the delete function in the repository is a suspend function, that's why we need to implement a coroutine
    // scope for deleteNote.

    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}