package com.bluelaned.animelist

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluelaned.animelist.databinding.ActivityAnimeDetailsBinding

class AnimeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimeDetailsBinding

    companion object {
        const val EXTRA_ANIME = "extra_anime"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val anime: Anime? = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_ANIME, Anime::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ANIME)
        }

        binding.titleDetails.text = anime?.name
        binding.authorName.text = anime?.author ?: ""
        binding.descriptionDetails.text = anime?.description ?: ""
        binding.cover.setImageResource(anime?.photo ?: 0)
    }
}