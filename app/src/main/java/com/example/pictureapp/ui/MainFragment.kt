package com.example.pictureapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pictureapp.databinding.FragmentMainBinding
import com.example.pictureapp.recycler.PictureAdapter
import com.example.pictureapp.viewmodel.PictureViewModel

class MainFragment(
    private val viewModel: PictureViewModel
) : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: PictureAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = PictureAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.loadedPictures.observe(viewLifecycleOwner) { pictures ->
            adapter.setPictures(pictures)
        }
    }
}