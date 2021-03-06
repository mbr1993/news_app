package com.example.news.ui.news.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.data.model.news.ArticleResponse
import com.example.news.databinding.ViewHolderLastNewsBinding

class LastNewsAdapter(
    private val newsOnClick: (articleResponse: ArticleResponse) -> Unit
) : RecyclerView.Adapter<LastNewsAdapter.NewsViewHolder>() {

    var items: MutableList<ArticleResponse> = mutableListOf()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ViewHolderLastNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(newItems: List<ArticleResponse>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addData(newItems: List<ArticleResponse>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(
        private val binding: ViewHolderLastNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ArticleResponse) {
            with(binding) {
                root.setOnClickListener { newsOnClick.invoke(data) }
                newsImageSdv.setImageURI(data.imageUrl)
                titleTv.text = data.title
                publishedAtTv.text = data.publishedAt ?: ""
            }
        }
    }
}