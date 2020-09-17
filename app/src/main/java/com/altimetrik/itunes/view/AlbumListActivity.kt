package com.altimetrik.itunes.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altimetrik.itunes.R
import com.altimetrik.itunes.adapters.AlbumSearchAdapter
import com.altimetrik.itunes.dbController.entity.AlbumResultsEntity
import com.altimetrik.itunes.utils.AppUtility.isNetworkOnline
import com.altimetrik.itunes.viewmodel.AlbumViewModel
import com.devop.aashish.sample.core.AppExecutors
import java.util.*
import kotlin.collections.ArrayList

class AlbumListActivity : AppCompatActivity() {

    private var adapter: AlbumSearchAdapter? = null
    private var albumListResultsOriginal: MutableList<AlbumResultsEntity>? = ArrayList();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search)
                .actionView as SearchView
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(componentName))
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapter!!.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                adapter!!.filter.filter(query)
                return false
            }
        })
        return true
    }

    private fun init() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvAlbumList)
        recyclerView.layoutManager = LinearLayoutManager(this@AlbumListActivity)
        adapter = AlbumSearchAdapter(this)
        recyclerView.adapter = adapter
        val viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        if (isNetworkOnline(this@AlbumListActivity)) {
            viewModel.album.observe(this, Observer { album ->
                if (album != null) {
                    albumListResultsOriginal = album.results!!.distinctBy { it.trackName }.toMutableList()
                    AppExecutors.instance.networkIO().execute {
                        viewModel.dbInsert(albumListResultsOriginal!!);
                    }
                    adapter!!.setResults(this.albumListResultsOriginal!!.sortedBy { it.releaseDate })
                }
            })
        } else {
            AppExecutors.instance.diskIO().execute {
                albumListResultsOriginal = viewModel.fetchDb().toMutableList()
                if (albumListResultsOriginal!!.isEmpty()) {
                    Toast.makeText(this, getString(R.string.network_errro), Toast.LENGTH_LONG).show()
                } else {
                    kotlin.run {
                        adapter!!.setResults(this.albumListResultsOriginal!!.sortedBy { it.releaseDate })

                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_collect_name -> {
                adapter!!.setResults(albumListResultsOriginal!!.sortedBy { album -> album.collectionName });
                true
            }
            R.id.menu_tack_name -> {
                adapter!!.setResults(albumListResultsOriginal!!.sortedBy { album -> album.trackName });
                true
            }
            R.id.menu_artist_name -> {
                adapter!!.setResults(albumListResultsOriginal!!.sortedBy { album -> album.artistName });
                true
            }
            R.id.menu_price -> {
                adapter!!.setResults(albumListResultsOriginal!!.sortedBy { album -> album.collectionPrice });
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}