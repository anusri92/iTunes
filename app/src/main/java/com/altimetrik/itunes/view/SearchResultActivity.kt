package com.altimetrik.itunes.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.altimetrik.itunes.R
import com.altimetrik.itunes.dbController.entity.AlbumResultsEntity
import com.altimetrik.itunes.utils.AppConstants
import com.altimetrik.itunes.utils.AppUtility
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.xml.sax.Parser
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream

class SearchResultActivity : AppCompatActivity() {

    private var trackImage: ImageView? = null;
    private var tvTRackName: TextView? = null;
    private var tvArtistName: TextView? = null;
    private var tvCollectionName: TextView? = null;
    private var tvCollectionPrice: TextView? = null;
    private var tvReleaseDate: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_result_activity)
        init()
        getIntentValue()
    }

    private fun getIntentValue() {
        val albumResult: String? = intent.extras!!.getString(AppConstants.ALBUM_INTENT)
        try {
            val gson = Gson()
            val albumResultsEntity: AlbumResultsEntity = gson.fromJson(albumResult, AlbumResultsEntity::class.java)
            kotlin.run {
                tvArtistName!!.text = getString(R.string.artist_name_label).plus(albumResultsEntity.artistName)
                tvTRackName!!.text = getString(R.string.track_name).plus(albumResultsEntity.trackName)
                tvCollectionPrice!!.text = getString(R.string.collection_price).plus(albumResultsEntity.collectionPrice.toString())
                tvCollectionName!!.text = getString(R.string.collection_name_label).plus(albumResultsEntity.collectionName.toString())
                tvReleaseDate!!.text = getString(R.string.released_date_label).plus(albumResultsEntity.releaseDate)
                if (AppUtility.isNetworkOnline(this)) {
                    Glide.with(this)
                            .load(albumResultsEntity.artworkUrl100)
                            .placeholder(R.drawable.default_img)
                            .error(R.drawable.default_img)
                            .into(trackImage!!);
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    private fun init() {
        trackImage = findViewById(R.id.trackImg)
        tvTRackName = findViewById(R.id.tvTrackName)
        tvArtistName = findViewById(R.id.tvArtistName)
        tvCollectionName = findViewById(R.id.tvCollectionName)
        tvCollectionPrice = findViewById(R.id.tvPrice)
        tvReleaseDate = findViewById(R.id.tvReleaseDate)
    }


}