package com.example.pictureapp.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.pictureapp.data.PictureItem

class PictureDiffUtilCallback(
    private val oldList: List<PictureItem>,
    private val newList: List<PictureItem>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].url == newList[newItemPosition].url
    }
}