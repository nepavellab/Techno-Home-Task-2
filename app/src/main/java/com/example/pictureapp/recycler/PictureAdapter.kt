package com.example.pictureapp.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureapp.data.DataList
import com.example.pictureapp.data.PictureItem
import com.example.pictureapp.databinding.MediaItemBinding

class PictureAdapter(
    private val onLoad: () -> Unit
) : RecyclerView.Adapter<PictureViewHolder>() {
    private var pictureList = DataList.imageList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(
            MediaItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = pictureList.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val picture = pictureList[position]
        holder.bind(picture)
        if (position == pictureList.size - 1) {
            onLoad()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPictures(pictures: MutableList<PictureItem>) {
        /*val calculatedDiff = DiffUtil.calculateDiff(
            PictureDiffUtilCallback(pictureList, pictures)
        )*/
        pictureList = pictures
        //calculatedDiff.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }
}