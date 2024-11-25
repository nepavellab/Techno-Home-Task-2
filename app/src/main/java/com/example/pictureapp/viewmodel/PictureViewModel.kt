package com.example.pictureapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureapp.data.DataList
import com.example.pictureapp.server.PictureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class PictureViewModel : ViewModel() {
    val loadedPictures = MutableLiveData(DataList.imageList)
    val isLoading = MutableLiveData(false)
    val isError = MutableLiveData(false)

    fun loadPictures(count: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                isLoading.postValue(true)
                repeat(count) {
                    val picture = PictureRepository.fetchData()
                    DataList.imageList.add(picture)
                    loadedPictures.postValue(DataList.imageList)
                }
            } catch(exception: IOException) {
                isError.postValue(true)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}