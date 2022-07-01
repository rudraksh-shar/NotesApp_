package com.gmail.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes():LiveData<List<Note>>

    // Live data is basically lifecycle aware data, it is wrapper around any data.
    // we need a lifecycle owner to observe the livedata.
    //we will going to make aur main activity as a observer of this live data.

}