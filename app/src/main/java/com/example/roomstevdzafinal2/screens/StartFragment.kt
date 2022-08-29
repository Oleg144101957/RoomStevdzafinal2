package com.example.roomstevdzafinal2.screens

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomstevdzafinal2.R
import com.example.roomstevdzafinal2.databinding.FragmentAddBinding
import com.example.roomstevdzafinal2.databinding.FragmentStartBinding
import com.example.roomstevdzafinal2.models.Note
import com.example.roomstevdzafinal2.viewmodel.NoteViewModel


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: AdapterNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        adapter = AdapterNote()
        recycler = binding.recyclerView
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        mViewModel.mLiveData.observe(viewLifecycleOwner, {
            adapter.setList(it)
        })

        binding.addNote.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_addFragment)
        }

        val swipeGuest = object : SwipeGuest(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val noteToDelete = adapter.listOfNotes[viewHolder.adapterPosition]
                mViewModel.deleteNote(noteToDelete)
                adapter.notifyDataSetChanged()
                Toast.makeText(requireContext(), "Swiped", Toast.LENGTH_LONG).show()
            }
        }

        val touchHelper = ItemTouchHelper(swipeGuest)
        touchHelper.attachToRecyclerView(recycler)

        return binding.root
    }


}