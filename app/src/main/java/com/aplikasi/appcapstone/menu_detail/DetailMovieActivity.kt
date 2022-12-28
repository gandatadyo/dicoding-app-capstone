package com.aplikasi.appcapstone.menu_detail

import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aplikasi.appcapstone.R
import com.aplikasi.core.data.source.local.entity.MovieEntity
import com.aplikasi.appcapstone.databinding.ActivityDetailBinding
import com.aplikasi.core.domain.model.Movie
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data_detail_movie"
    }

    private lateinit var binding : ActivityDetailBinding

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moviem = if(VERSION.SDK_INT>=33) {
            intent.getParcelableExtra(EXTRA_DATA, Movie::class.java)
        }else{
            intent.getParcelableExtra(EXTRA_DATA)
        }

        if(moviem!=null){
            detailMovieViewModel.movieData.value = moviem
        }

        binding.btnFavorite.setOnClickListener {
            detailMovieViewModel.setFavoriteMovie(moviem!!)
        }

        detailMovieViewModel.movieData.observe(this){
            binding.lblTitle.text = it.name
            binding.lblDesc.text = it.description
            val urlImg = this.getString(R.string.base_url_img) + "/" + it.image
            Glide.with(this)
                .load(urlImg)
                .into(binding.imgData)
            if (it.isFavorite) {
                binding.btnFavorite.setImageDrawable(this.getDrawable(R.drawable.ic_bookmarked_white))
            } else {
                binding.btnFavorite.setImageDrawable(this.getDrawable(R.drawable.ic_bookmark_white))
            }
        }
    }
}