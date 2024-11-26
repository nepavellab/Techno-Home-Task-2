package com.example.pictureapp.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.pictureapp.R
import com.example.pictureapp.data.PictureItem
import com.example.pictureapp.databinding.MediaItemBinding
import com.squareup.picasso.Picasso

class PictureViewHolder(
    private val binding: MediaItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(picture: PictureItem) {
        with(binding) {
            Picasso.get()
                .load(picture.url)
                .placeholder(R.drawable.default_picture)
                .into(ivMedia)
        }
    }
}