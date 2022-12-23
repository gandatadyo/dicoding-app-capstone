package com.aplikasi.appcapstone.menu_search_movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.appcapstone.R
import com.aplikasi.appcapstone.adapter.MovieAdapter
import com.aplikasi.appcapstone.databinding.ActivitySearchMovieBinding
import com.aplikasi.appcapstone.menu_detail.DetailMovieActivity
import com.aplikasi.core.data.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMovieActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA_SEARCH = "search"
    }

    private lateinit var binding :ActivitySearchMovieBinding

    private val searchMovieViewModel: SearchMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtSearch.setText(intent.getStringExtra(EXTRA_DATA_SEARCH))

        binding.btnSearch.setOnClickListener {
            searchMovieViewModel.search.value = binding.edtSearch.text.toString()
        }

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        searchMovieViewModel.movieList.observe(this) { moviem ->
            if (moviem != null) {
                when (moviem) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.setData(moviem.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = moviem.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }

        with(binding.rvData) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        if(binding.edtSearch.text.toString()!=""){
            searchMovieViewModel.search.value = binding.edtSearch.text.toString()
        }
    }
}