package com.example.notep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notep.databinding.FragmentSecondBinding
import com.example.notep.model.NoteRepository
import com.example.notep.viewmodel.NoteViewModel


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val noteRepository = NoteRepository()
        val noteViewModel  = ViewModelProvider(requireActivity(), NoteModelFactory(noteRepository))[NoteViewModel::class.java]
        val currentFile = noteViewModel.getCurrentNote()

        val noteEditor = binding.notepEditor
        val noteTitle = binding.notepTitle

        if(currentFile.isNotEmpty()){
            noteTitle.setText(currentFile)
            noteEditor.setText(noteViewModel.readNote(currentFile))
        }

        binding.buttonSecond.setOnClickListener {
            noteViewModel.saveNote(noteTitle.text.toString(), noteEditor.text.toString())
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}