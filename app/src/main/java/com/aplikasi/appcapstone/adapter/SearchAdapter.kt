package com.aplikasi.appcapstone.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.appcapstone.R
import com.aplikasi.appcapstone.databinding.AdapterMovieBinding
import com.aplikasi.core.data.source.remote.response.ResultsMovie
import com.bumptech.glide.Glide
import java.util.ArrayList

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ListViewHolder>() {

    private var listData = ArrayList<ResultsMovie>()
    var onItemClick: ((ResultsMovie) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<ResultsMovie>?) {
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
        fun bind(data: ResultsMovie) {
            with(binding) {
                val urlImg = itemView.context.getString(R.string.base_url_img)+"/"+data.backdropPath
                Glide.with(itemView.context)
                    .load(urlImg)
                    .into(binding.imgData)
                binding.lblName.text = data.originalTitle
                binding.lblDesc.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}