package com.example.pictureapp.server

import com.google.gson.annotations.SerializedName

data class PictureResponse (
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: String
)