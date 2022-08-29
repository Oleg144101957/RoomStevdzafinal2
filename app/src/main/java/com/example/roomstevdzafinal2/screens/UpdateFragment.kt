package com.example.roomstevdzafinal2.screens

import android.icu.text.CaseMap
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.roomstevdzafinal2.R
import com.example.roomstevdzafinal2.databinding.FragmentUpdateBinding
import com.example.roomstevdzafinal2.models.Note
import com.example.roomstevdzafinal2.viewmodel.NoteViewModel


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)

        binding.noteTitle.setText(args.argNoteForUpdate.title)
        binding.description.setText(args.argNoteForUpdate.description)
        binding.save.setOnClickListener {
            val title = binding.noteTitle.text.toString()
            val description = binding.description.text.toString()
            if (checkInput(title, description)){
                val noteToUpdate = Note(args.argNoteForUpdate.id, title, description)
                mViewModel.updateNote(noteToUpdate)
                Toast.makeText(requireContext(), "Udated", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateFragment_to_startFragment)
            } else {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun checkInput(title: String, description: String) : Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

}