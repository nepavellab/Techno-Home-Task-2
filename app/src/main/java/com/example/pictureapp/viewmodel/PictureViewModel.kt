package com.example.pictureapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureapp.data.DataList
import com.example.pictureapp.server.PictureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PictureViewModel : ViewModel() {
    val loadedPictures = MutableLiveData(DataList.imageList)

    fun loadPicture() {
        CoroutineScope(Dispatchers.IO).launch {
            val picture = PictureRepository.fetchData()
            DataList.imageList.add(picture)
            loadedPictures.postValue(DataList.imageList)
        }
    }
}