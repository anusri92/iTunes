package com.altimetrik.itunes.servicecall

import com.altimetrik.itunes.model.Album
import com.altimetrik.itunes.utils.ServerUtils
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetAlbumListService {
    @GET(ServerUtils.API_SEARCH)
    fun getAlbumList(
            @Query("term") query: String?
    ): Call<Album?>?
}