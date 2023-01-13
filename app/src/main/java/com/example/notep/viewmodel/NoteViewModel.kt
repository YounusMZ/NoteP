package com.example.notep.viewmodel

import androidx.lifecycle.*
import com.example.notep.model.NoteRepository


class NoteViewModel(private val noteRepository : NoteRepository) : ViewModel(){

    private var currentNote = ""


    fun getCurrentNote() : String{
        return currentNote
    }

    fun setCurrentNote(noteName: String){
        currentNote = noteName
    }

    fun getNoteListString() : List<String>{
        return noteRepository.getFileListString()
    }

    fun saveNote(noteName: String, noteContent: String){
        noteRepository.saveFile(noteName, noteContent)
    }

    fun readNote(noteName:String) : String{
        val textList = noteRepository.readFile(noteName)
        var textSerial = ""

        textList.forEach {
            textSerial += it
        }
        return textSerial
    }

    fun deleteNote(noteName: String){
        noteRepository.deleteFile(noteName)
    }
}