package com.gmail.notesapp
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//This is an entity class in which we creates entities.

@Entity(tableName = "notes_table")

//The arguments in the below functions are the columns of the table notes_app
//we will going to create an object of class Note and we will pass the arguments to it which is
//basically the columns value of it.

class Note(@ColumnInfo(name = "text") val text:String) {

    @PrimaryKey(autoGenerate = true) var id  = 0
}
//now after creating an entity, we must create a DAO( Data access object )

