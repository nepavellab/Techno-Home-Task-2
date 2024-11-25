package com.example.pictureapp.server

import retrofit2.Response
import retrofit2.http.GET

interface PictureService {
    @GET("random")
    suspend fun getPicture(): Response<PictureResponse>
}