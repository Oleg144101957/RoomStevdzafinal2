package com.example.roomstevdzafinal2.screens

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
import com.example.roomstevdzafinal2.R
import com.example.roomstevdzafinal2.databinding.FragmentAddBinding
import com.example.roomstevdzafinal2.models.Note
import com.example.roomstevdzafinal2.viewmodel.NoteViewModel


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        val mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding.save.setOnClickListener {
            val title = binding.noteTitle.text.toString()
            val description = binding.description.text.toString()

            if (checkInput(title, description)){
                val noteToSave = Note(0, title, description)
                mViewModel.addNote(noteToSave)
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addFragment_to_startFragment)
            } else {
                Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun checkInput(title: String, description: String): Boolean{
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }


}