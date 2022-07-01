package com.gmail.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//this is NotesRecyclerViewAdapter


class NotesRVAdapter(private val context: Context, private val listener:INotesRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton: ImageView = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    private val allNotes = ArrayList<Note>()

    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note ,parent,false)
        val viewHolder = NoteViewHolder(view)
        viewHolder.deleteButton.setOnClickListener{
            listener.onNoteClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text

    }

    override fun getItemCount(): Int {
      return allNotes.size
    }

}

// to handle clicks of the notes ( delete button ), we need an interface and inside the interface, we need to declare the things
// that we want to trigger
interface INotesRVAdapter{
    fun onNoteClicked(note:Note)
}














