package com.example.notep.model

import android.content.Context
import android.os.Environment
import java.io.File

class NoteRepository() {

    private val saveDirectory = File(Environment.getExternalStorageDirectory().absolutePath + "/Documents")

    private fun getFileExtension(fileName: String) : String {
        return fileName.split(".").last()
    }

    fun getFileListString() : List<String>{
        val currentFolder = File(saveDirectory.toString())
        val currentFolderFiles = currentFolder.listFiles()

        if(currentFolderFiles.isNullOrEmpty()){
            return ArrayList<String>()
        }
        else {
            val currentTextFiles = currentFolderFiles.filter { getFileExtension(it.toString()) == "txt" }
            val fileListString: List<String> = currentTextFiles.map {
                it.toString().substringAfterLast("/")
            }
            return fileListString
        }
    }

    fun saveFile(fileName: String, fileContent: String){
        val filesDir = File(saveDirectory.toString())

        if(!filesDir.exists()){
            filesDir.mkdirs()
        }

        if(fileName.isNotEmpty()) {
            lateinit var currentFile : File

            if(getFileExtension(fileName) != "txt"){
                currentFile = File(saveDirectory, fileName + ".txt")
            }
            else {
                currentFile = File(saveDirectory, fileName)
            }
            if (currentFile.exists()) {
                currentFile.delete()
            }

            currentFile.printWriter().use {
                it.println(fileContent)
            }
        }
    }

    fun readFile(fileName: String) : List<String>{
        lateinit var fileContentList : List<String>
        val currentFile = File(saveDirectory, fileName)

        fileContentList = currentFile.bufferedReader().readLines()
        return fileContentList
    }

    fun deleteFile(fileName: String){
        val currentFile = File(saveDirectory, fileName)
        currentFile.delete()
    }

}