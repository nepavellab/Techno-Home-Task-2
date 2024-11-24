package com.example.pictureapp.server

import com.example.pictureapp.data.PictureResponse
import com.example.pictureapp.data.PictureItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.UUID

interface PictureService {
    @GET("random")
    suspend fun getPicture(): Response<PictureResponse>
}

object PictureRepository {
    private const val BASE_URL = "https://dog.ceo/api/breeds/image/"
    private val api =
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PictureService::class.java)

    suspend fun fetchData(): PictureItem {
        val response = api.getPicture()
        val responseBody = response.body()!!
        val picture = PictureItem(UUID.randomUUID(), responseBody.message)
        return picture
    }
}