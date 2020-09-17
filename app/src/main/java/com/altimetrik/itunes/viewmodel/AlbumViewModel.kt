package com.altimetrik.itunes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.altimetrik.itunes.dbController.entity.AlbumResultsEntity
import com.altimetrik.itunes.dbController.AppDatabase
import com.altimetrik.itunes.model.Album
import com.altimetrik.itunes.servicecall.GetAlbumListService
import com.altimetrik.itunes.servicecall.RetrofitRestClient.client
import com.devop.aashish.sample.core.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AlbumViewModel(application: Application) : AndroidViewModel(application) {
    fun dbInsert(results: List<AlbumResultsEntity>) {
        AppDatabase.getInstance().getAlbumResult().insertAll(results);
    }

    fun fetchDb(): List<AlbumResultsEntity> {
            var list: List<AlbumResultsEntity> = AppDatabase.getInstance().getAlbumResult().fetchAll()
            print(" album list--" + list);
        return list;
    }

    val album: MutableLiveData<Album?>
        get() {
            val albumMutableLiveData = MutableLiveData<Album?>()
            client.create(GetAlbumListService::class.java).getAlbumList("all")!!.enqueue(object : Callback<Album?> {
                override fun onResponse(call: Call<Album?>, response: Response<Album?>) {
                    if (response.body() != null) {
                        albumMutableLiveData.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<Album?>, t: Throwable) {
                    albumMutableLiveData.postValue(null)
                }
            })
            return albumMutableLiveData
        }


}