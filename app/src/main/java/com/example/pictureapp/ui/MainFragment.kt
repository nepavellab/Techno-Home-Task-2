package com.example.pictureapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.pictureapp.databinding.FragmentMainBinding
import com.example.pictureapp.recycler.PictureAdapter
import com.example.pictureapp.viewmodel.PictureViewModel

class MainFragment(
    private val viewModel: PictureViewModel
) : Fragment() {
    companion object {
        private const val LOAD_IMAGE_COUNT = 10
    }
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: PictureAdapter
    private lateinit var progressBar: ProgressBar
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = PictureAdapter { viewModel.loadPictures(LOAD_IMAGE_COUNT) }
        binding.recyclerView.adapter = adapter
        progressBar = binding.progressBar
        setObservers()
    }

    private fun setObservers() {
        viewModel.loadedPictures.observe(viewLifecycleOwner) { pictures ->
            adapter.setPictures(pictures)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility =
                if (isLoading == true) {
                    ProgressBar.VISIBLE
                } else {
                    ProgressBar.GONE
                }
        }

        viewModel.isError.observe(viewLifecycleOwner) {
            handler.post {
                val reloadFragment = ReloadFragment {
                    viewModel.loadPictures(LOAD_IMAGE_COUNT)
                    parentFragmentManager.popBackStack()
                }

                parentFragmentManager
                    .beginTransaction()
                    .add(binding.root.id, reloadFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}