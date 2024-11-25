package com.example.pictureapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.pictureapp.databinding.ActivityMainBinding
import com.example.pictureapp.viewmodel.PictureViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainFragment: MainFragment
    private lateinit var viewModel: PictureViewModel
    private lateinit var binding: ActivityMainBinding
    companion object {
        private const val INIT_IMAGE_COUNT = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PictureViewModel::class.java]
        mainFragment = MainFragment(viewModel)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(binding.main.id,  mainFragment)
                .commit()
            viewModel.loadPictures(INIT_IMAGE_COUNT)
        }
    }
}