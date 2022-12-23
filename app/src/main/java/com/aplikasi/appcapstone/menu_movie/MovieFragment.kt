package com.aplikasi.appcapstone.menu_movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.appcapstone.R
import com.aplikasi.core.data.Resource
import com.aplikasi.appcapstone.adapter.MovieAdapter
import com.aplikasi.appcapstone.databinding.FragmentMovieBinding
import com.aplikasi.appcapstone.menu_detail.DetailMovieActivity
import com.aplikasi.appcapstone.menu_search_movie.SearchMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    // private lateinit var movieViewModel: MovieViewModel
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        binding.btnSearch.setOnClickListener {
            val intent = Intent(requireContext(),SearchMovieActivity::class.java)
            intent.putExtra(SearchMovieActivity.EXTRA_DATA_SEARCH,binding.edtSearch.text.toString())
            startActivity(intent)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            movieViewModel.movieList.observe(viewLifecycleOwner) { moviem ->
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}