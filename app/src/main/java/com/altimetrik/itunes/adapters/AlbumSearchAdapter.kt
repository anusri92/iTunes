package com.altimetrik.itunes.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.altimetrik.itunes.R
import com.altimetrik.itunes.adapters.AlbumSearchAdapter.AlbumResultHolder
import com.altimetrik.itunes.dbController.entity.AlbumResultsEntity
import com.altimetrik.itunes.utils.AppConstants
import com.altimetrik.itunes.view.SearchResultActivity
import com.google.gson.GsonBuilder
import java.util.*

class AlbumSearchAdapter(activity: Activity) : RecyclerView.Adapter<AlbumResultHolder>(), Filterable {
    private var act: Activity = activity;
    private var results: List<AlbumResultsEntity> = ArrayList()
    private var albumSearchFilteredList: List<AlbumResultsEntity> = ArrayList()
    private var myFavList: MutableList<AlbumResultsEntity> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumResultHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_album, parent, false)
        return AlbumResultHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlbumResultHolder, position: Int) {
        val (_, _, _, _, _, _, _, _, _, _, trackName, _, _, _, _, _, _, _, _, _, _, _, _, _, releaseDate, _, _, _, collectionPrice, _, _, _, _, _, _, _, _, artistName) = albumSearchFilteredList[position]
        holder.tvArtistName.text = act.getString(R.string.artist_name_label).plus(artistName)
        holder.tvTrackName.text =  act.getString(R.string.track_name_label).plus(trackName)
        holder.tvReleaseDate.text =  act.getString(R.string.released_date_label).plus(releaseDate.toString())
        holder.tvPrice.text =  act.getString(R.string.collection_price_label).plus(collectionPrice.toString())
        holder.lyMain.setOnClickListener {
            val gson = GsonBuilder().setPrettyPrinting().create()
            val outputJson: String = gson.toJson(albumSearchFilteredList[position])
            val intent = Intent(act, SearchResultActivity::class.java)
            intent.putExtra(AppConstants.ALBUM_INTENT, outputJson)
            act.startActivity(intent)
        }
        holder.myFav.setOnClickListener {
            if(myFavList.contains(albumSearchFilteredList[position])) {
                myFavList.remove(albumSearchFilteredList[position])
                holder.myFav.setImageResource(R.drawable.ic_fav_foreground)
            }else {
                myFavList.add(albumSearchFilteredList[position])
                holder.myFav.setImageResource(R.drawable.ic_favortie_foreground)
            }
        }
    }

    override fun getItemCount(): Int {
        return albumSearchFilteredList.size
    }

    fun setResults(results: List<AlbumResultsEntity>) {
        this.results = results
        albumSearchFilteredList = results
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                albumSearchFilteredList = if (charString.isEmpty()) {
                    results
                } else {
                    val filteredList: MutableList<AlbumResultsEntity> = ArrayList()
                    for (row in albumSearchFilteredList) {
                        if (row.trackName!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = albumSearchFilteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                albumSearchFilteredList = results.values as List<AlbumResultsEntity>
                notifyDataSetChanged()
            }
        }
    }

    class AlbumResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvArtistName: TextView = itemView.findViewById(R.id.tvArtistName)
        val tvTrackName: TextView = itemView.findViewById(R.id.tvTrackName)
        val tvReleaseDate: TextView = itemView.findViewById(R.id.tvReleaseDate)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val lyMain: LinearLayout = itemView.findViewById(R.id.lyMain)
        val myFav: ImageView = itemView.findViewById(R.id.imgFav)

    }
}
