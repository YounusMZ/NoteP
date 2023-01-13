package com.example.notep

import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notep.viewmodel.NoteViewModel

class NoteAdapter(private var fileList: List<String>, private val fragment: Fragment, private val viewModel: NoteViewModel) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val noteTextView: Button = view.findViewById(R.id.note)
        val deleteNoteButton : Button = view.findViewById(R.id.note_delete)
    }

    override fun getItemCount(): Int {
        return fileList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTextView.text = fileList[position]

        holder.noteTextView.setOnClickListener{
            viewModel.setCurrentNote(holder.noteTextView.text.toString())
            fragment.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        holder.deleteNoteButton.setOnClickListener{
            viewModel.deleteNote(fileList[position])
            fileList = fileList.filter {
                it != fileList[position]
            }
            notifyItemRemoved(position)
        }
    }
}