package com.aplikasi.appcapstone.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.appcapstone.R
import com.aplikasi.appcapstone.databinding.AdapterMovieBinding
import com.aplikasi.core.data.source.local.entity.MovieEntity
import com.aplikasi.core.domain.model.Movie
import com.bumptech.glide.Glide
import java.util.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = AdapterMovieBinding.inflate(LayoutInflater.from(parent.context))
        return ListViewHolder(binding.root)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = AdapterMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                val urlImg = itemView.context.getString(R.string.base_url_img)+"/"+data.image
                Glide.with(itemView.context)
                    .load(urlImg)
                    .into(binding.imgData)
                binding.lblName.text = data.name
                binding.lblDesc.text = data.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}