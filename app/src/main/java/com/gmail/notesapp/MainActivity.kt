package com.gmail.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//use this website:- https://developer.android.com/codelabs/android-room-with-a-view-kotlin#1

class MainActivity : AppCompatActivity(), INotesRVAdapter {


    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //now its time to get the recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter


        //we are attaching our main activity to this viewModel, that's why the owner will be this.
        //we can attach several activities or fragments to this viewModel.
        viewModel =  ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        // because this allNotes is a liveData, that's why we need an observer to observe the changes inside this,
        //so we make our main activity ( a type of lifeCycleOwner ) to be this liveDataObserver

        //this liveData will be lifeCycle aware ( lifeCycle of owner )and it knows whether the owner is destroyed
        //or create, or resume and it will show the updated data only when the observer is in active state.
        viewModel.allNotes.observe(this, Observer { list->
            list?.let {
                adapter.updateList(it)
            }

        })



    }

    override fun onNoteClicked(note: Note) {

    //Now we need that on clicking of a note, the note should get deleted.
    // So to delete the note, the main activity will call on viewModel and then viewModel will call the
    // repository and the repository will call delete via DAO  .

        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitNote(view: View) {
       val noteText =  findViewById<EditText>(R.id.input).text.toString()
        if(noteText.isNotEmpty()){
            val note = Note(noteText)
            viewModel.insertNote(note)
            Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_SHORT).show()
            findViewById<EditText>(R.id.input).text.clear()
        }
    }


}