package com.example.notep

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notep.databinding.FragmentFirstBinding
import com.example.notep.model.NoteRepository
import com.example.notep.viewmodel.NoteViewModel


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(this){
            val homeIntent = Intent(Intent.ACTION_MAIN)
            homeIntent.addCategory(Intent.CATEGORY_HOME)
            homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(homeIntent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteRepository = NoteRepository()
        val noteViewModel  = ViewModelProvider(requireActivity(), NoteModelFactory(noteRepository))[NoteViewModel::class.java]
        val noteList : List<String> = noteViewModel.getNoteListString()

        val recyclerView = view.findViewById<RecyclerView>(R.id.note_recycler)
        val noteAdapter = NoteAdapter(noteList, this, noteViewModel)

        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = noteAdapter

        binding.fab.setOnClickListener{
            noteViewModel.setCurrentNote("")
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}