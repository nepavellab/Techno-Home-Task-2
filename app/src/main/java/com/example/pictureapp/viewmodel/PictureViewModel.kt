package com.example.pictureapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureapp.data.PictureItem
import com.example.pictureapp.server.PictureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class PictureViewModel : ViewModel() {
    val loadedPictures = MutableLiveData<MutableList<PictureItem>>()
    val isLoading = MutableLiveData(false)
    val isError = MutableLiveData(false)

    init {
        loadedPictures.value = mutableListOf()
    }

    fun loadPictures(count: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                isLoading.postValue(true)
                repeat(count) {
                    val picture = PictureRepository.fetchData()
                    loadedPictures.value?.add(picture)
                }
                loadedPictures.postValue(loadedPictures.value)
            } catch(exception: IOException) {
                isError.postValue(true)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}