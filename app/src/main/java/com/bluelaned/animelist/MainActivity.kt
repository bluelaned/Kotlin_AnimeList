package com.bluelaned.animelist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAnimes: RecyclerView
    private val list = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnimes = findViewById(R.id.rv_animes)
        rvAnimes.setHasFixedSize(true)

        list.addAll(getListAnimes())
        showRecyclerList()
    }

    private fun getListAnimes(): ArrayList<Anime>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataAuthor = resources.getStringArray(R.array.data_author)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listAnime = ArrayList<Anime>()
        for (i in dataName.indices) {
            val anime = Anime(
                dataName[i],
                dataAuthor[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1),
            )
            listAnime.add(anime)
        }
        return listAnime
    }

    private fun showRecyclerList() {
        rvAnimes.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = ListAnimeAdapter(list)
        rvAnimes.adapter = listAnimeAdapter

        listAnimeAdapter.setOnItemClickCallback(object : ListAnimeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Anime) {
                val moveAnimeDetail = Intent(this@MainActivity, AnimeDetailsActivity::class.java)
                moveAnimeDetail.putExtra(AnimeDetailsActivity.EXTRA_ANIME, data)
                startActivity(moveAnimeDetail)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}